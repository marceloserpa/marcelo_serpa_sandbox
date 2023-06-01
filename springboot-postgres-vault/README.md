./vault server -dev


export VAULT_TOKEN="TOKEN"
./vault kv put secret/db password=123456 username=marceloserpa

