# strykerjs-node-test-poc

## Prerequisites

- Node.js >= 23.6 (native TypeScript support in `node:test`, no flags needed)

## Install

```sh
npm install
```

## Running the tests

```sh
npm test
```

This runs `node --test` directly against the `.ts` files under `test/` — Node's built-in TypeScript
stripping means no build step or extra loader is needed. StrykerJS has no official `node:test` runner
plugin, so mutation is wired up via Stryker's built-in **command runner**: for every mutant it just runs
`npm test` and treats a zero exit code as "survived" and a non-zero exit code as "killed" (see
[`stryker.conf.json`](./stryker.conf.json)). The trade-off is that the command runner only supports
`coverageAnalysis: "off"`, so mutants in code with zero test coverage show up as "survived" rather than
"no coverage".

## Running Stryker

```sh
npm run mutation
```

This mutates everything under `src/`, reruns `npm test` against each mutant, and prints a summary plus an HTML
report at:

```
reports/mutation/mutation.html
```

Open it with:

```sh
open reports/mutation/mutation.html
```

Mutation behavior is configured in [`stryker.conf.json`](./stryker.conf.json).

## Summary

- **killed**: the mutation broke the code and a test caught it.
- **survived**: the mutation broke the code but no test noticed.
- **no coverage**: not distinguishable from "survived" here, since the command runner requires `coverageAnalysis: "off"`.

## Project layout

```
src/
  calculator.ts
  stringUtils.ts
  collections.ts
  recursion.ts
  validator.ts
  orderProcessor.ts

test/
  *.test.ts
```


## Sample result

```
Ran 0.98 tests per mutant on average.
-------------------|------------------|----------|-----------|------------|----------|----------|
                   | % Mutation score |          |           |            |          |          |
File               |  total | covered | # killed | # timeout | # survived | # no cov | # errors |
-------------------|--------|---------|----------|-----------|------------|----------|----------|
All files          |  76.69 |   76.69 |      176 |         5 |         55 |        0 |        0 |
 calculator.ts     |  73.68 |   73.68 |       28 |         0 |         10 |        0 |        0 |
 collections.ts    |  84.21 |   84.21 |       32 |         0 |          6 |        0 |        0 |
 orderProcessor.ts |  71.43 |   71.43 |       25 |         0 |         10 |        0 |        0 |
 recursion.ts      |  71.43 |   71.43 |       35 |         5 |         16 |        0 |        0 |
 stringUtils.ts    | 100.00 |  100.00 |       21 |         0 |          0 |        0 |        0 |
 validator.ts      |  72.92 |   72.92 |       35 |         0 |         13 |        0 |        0 |
-------------------|--------|---------|----------|-----------|------------|----------|----------|
```
