import argparse
import requests
import time
import json
from pathlib import Path
import configparser

def load_properties(file_path: Path) -> dict:
    config = configparser.ConfigParser()
    config.optionxform = str  # preserve case sensitivity
    with file_path.open("r", encoding="utf-8") as f:
        file_content = f"[DEFAULT]\n{f.read()}"
    config.read_string(file_content)
    return dict(config["DEFAULT"])

def replace_placeholders(data: str, props: dict):
    for k,v in props.items():
        new_string = data.replace("@"+k, v)
    return new_string 

def main(env: str) :

    connectors = ["jdbc-source-connector"]

    url = "http://localhost:8083/connectors"

    for connector in connectors:
        print(f">> Starting {connector} setup")

        props_path = Path(f"../connectors/{connector}-{env}.properties")
        if not props_path.exists():
            print(f"!! Properties file not found: {props_path}")
            continue

        props = load_properties(props_path)
        
        file_path = Path(f"../connectors/{connector}.json")
        with file_path.open("r", encoding="utf-8") as file:
            content = file.read()
            content = replace_placeholders(content, props)
            payload = json.loads(content)

        print(payload)

        response = requests.post(
            url,
            headers={
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            json=payload
        )

        print(f"Response: {response.status_code} - {response.text}")
        
        time.sleep(2)
        print(f">> Completed {connector} setup")

if __name__ == "__main__":

    parser = argparse.ArgumentParser(description="Kafka Connect connector setup script")
    parser.add_argument(
        "--env", 
        required=True, 
        help="Environment name (e.g., dev, qa, prod)"
    )
    args = parser.parse_args()

    main(args.env)