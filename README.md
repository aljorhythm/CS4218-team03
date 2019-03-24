# CS4218-Project

## Development

See [development.md](docs/development.md) for development environment notes.

### System Tests

#### Prerequisites

Build artifact (runnable .jar file) in Intellij by clicking Run -> Build. Artifact
has been set to build on project build.

#### Unix

##### Run

`sh systemTests/test.sh`

##### Explanation

1. `*.template` files eg. [systemTests/expected/run_and_exit.template](systemTests/expected/run_and_exit.template) files are python string formats
2. [systemTests/generate_expected_out.py](systemTests/generate_expected_out.py) inserts machine dependent variables (working directory, etc.) into templates,
generating `*.out` files. (Python 3)
3. Input tests exist in [systemTests/commandLines](systemTests/commandLines)
4. Input files are looped and run as input for [out/artifacts/CS4218_Shell_2019_jar/CS4218-Shell-2019.jar](out/artifacts/CS4218_Shell_2019_jar/CS4218-Shell-2019.jar)
5. Actual output are in [systemTests/actual](systemTests/actual) and compared with expected output.

All output and actual file names corresponds to its respective test input file.