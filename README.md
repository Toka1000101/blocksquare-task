# Blocksquare Coding Skills Assessment
## backend
Backend application uses posgresql running in docker container.
How to run backend application:
```
$ docker compose up
$ quarkus dev 
```
### Test data 
There is endpoint /problems/test which accepts array of objects. This is done for testing purposes. It adds problems and its solutions in database.
This body can be used for testing data:
```arm
[
    {
        "matrix": [
            [1, 1, 1, 1, 1],
            [1, 1, 0, 1, 0],
            [1, 1, 1, 1, 0],
            [0, 1, 0, 0, 1],
            [1, 1, 1, 0, 1]
        ]
    },
    {
        "matrix": [
            [1, 1, 1],
            [1, 1, 0],
            [1, 1, 1]
        ]
    },
        {
        "matrix": [
            [1, 1, 0],
            [1, 1, 0],
            [1, 1, 0]
        ]
    },
    {
        "matrix": [
            [0,0,0,1],
            [0,0,1,0],
            [0,1,0,0],
            [1,0,0,0]
        ]
    },
    {
        "matrix": [
            [0,0,0,0,0,0],
            [0,0,0,1,0,1],
            [0,0,0,0,0,0],
            [0,0,1,0,0,0],
            [0,0,0,0,0,0],
            [0,0,0,0,0,0]
        ]
    }
]

```

## frontend

How to run:
```
$ npm install 
$ npm start
```


## test
Main purpose of this project was to test algorithm that solves lights out game 
with Gaussian-Jordan elimination over GF(2)

Class LightsOut is also in backend project.