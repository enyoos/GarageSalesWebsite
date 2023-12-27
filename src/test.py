import requests

URL_VENDORS = "http://localhost:8080/api/vendor/"
ID          = 1

URL_UPDATE_POST = f"http://localhost:8080/api/vendor/{ID}/posts"

data_ = {
    "title" : "some title",
    "description" : "dont lecture me",
    "dateOfPublication" : "2022-11-22",
    "image"   : []
}

data = {
    "name" : "envyooss",
    "password" : "dontlectureme",
    "profil" : [],
    "posts" : []
}

def add_vendor ( dt ):
    content = requests.post ( URL_VENDORS, json = dt)
    print ( content )

def give_all_vendors ( ):
    content = requests.get( URL_VENDORS ).content
    print ( content ) 

def add_post ( dt ):
    content = requests.post ( URL_UPDATE_POST, json=dt).content
    print ( content )

def give_all_post_by_vendor_id ( id ):
    content = requests.get( URL_UPDATE_POST ).content
    print ( content )

def remove_all_post_by_vendor_id ( ):
    content = requests.delete( URL_UPDATE_POST ).content
    print ( content )




if __name__ == "__main__":

    print ( "testing..." )
    # add_vendor( data )
    # give_all_vendors()
    remove_all_post_by_vendor_id ( )
    add_post( data_ )
    # give_all_post_by_vendor_id( id = ID )