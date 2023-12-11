# for debugging the java RESTFUL APP
import requests
import json

URL = "http://localhost:8080/api/vendors/" # DO NOT FORGET THE LEADING SLASH HERE.

# with the full things 
dict_ = {
    "id" : 4398,
    "name" : "ilyas",
    "email" : "some@envyoos.xyz",
    "password" : "43843",
}


# content = requests.get( URL ).content

str_ = json.dumps ( dict_ )
content = requests.post( URL , data = dict_ ).content
print( content )

