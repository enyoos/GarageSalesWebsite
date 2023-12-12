# for debugging the java RESTFUL APP
import requests
import json

URL = "http://localhost:8080/api/vendors/" # DO NOT FORGET THE LEADING SLASH HERE.


# with the full things 
def get ( ):
    content = requests.get ( URL )
    print ( content.status_code )
    print ( content.content)

def post ( dict_ ):
    content = requests.post( URL, json = dict_ )
    print ( content.status_code )
    print ( content.content)

def delete ( id ):
    URL_DELETE = URL + f"private/delete/{id}"
    content = requests.post( URL_DELETE )
    print ( content.status_code )
    print( content.content )

def update ( dict_ ) :
    content = requests.post ( URL, json = dict_ )
    print ( content.status_code)
    print( content.content )

dict_ = {
    "id" : 1,
    "name" : "name",
    "email" : "2277786@crosemont.qc.ca",
    "password" : "438948",
}

dict__ = {
    "id" : 1,
    "name" : "name",
    "email" : "2277786@crosemont.qc.ca",
    "password" : "438948",
}

post ( dict_ )
get ()