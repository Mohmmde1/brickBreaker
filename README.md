# brickBreaker

## Environment Setup
- step 1 (create .env)
```bash
cp env.template .env
```

- step 2 (edit .env)
```text
# development or production
APP_ENV=development

# Project absolute path
BASE_DIR=c:/project-path
```

## Contribution workflow
```
$ git clone https://github.com/Mohmmde1/brickBreaker.git
$ git pull 
$ git checkout main
$ git checkout -b feature/example-feature
$ git add . 
$ git commit -m "Added/Updated something ..."
$ git push -u origin "feature/example-feature"
```

> Download [Github Desktop](https://desktop.github.com/ "Github Desktop") for easier workflow

**Important Notes** 
* run the same configuration provided in *.vscode* folder for vscode to avoid debugging issues
* Add [X] for every achievement in the todo list
* Do not merge with the master branch, only issue a pull request with revision from main contributers