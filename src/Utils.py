# utilities


def constructEntity ( name : str, email: str , passw : str ) -> dict :
    return {
        "name" : name,
        "email" : email, 
        "password" : passw,
    }

def OK( status_code : int ) -> bool: return status_code >= 200 and status_code <= 299