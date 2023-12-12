import os
import requests
from Utils import *
from flask import Flask, flash, redirect, render_template, \
     request, url_for, session
# from flask_wtf.csrf import CSRFProtect
from flask_simple_captcha import CAPTCHA

from dotenv import load_dotenv
load_dotenv()

sk = os.getenv("SECRET_KEY")

POST_API_URL = "http://localhost:8080/api/vendors/"

## init the captcha configs
CONFIG = {
    'SECRET_CAPTCHA_KEY': 'LONG_KEY',
    'CAPTCHA_LENGTH': 6,
    'CAPTCHA_DIGITS': False,
    'EXPIRE_SECONDS': 600,
}
SIMPLE_CAPTCHA = CAPTCHA(config=CONFIG)

app = Flask(__name__)
# csrf = CSRFProtect(app)
app = SIMPLE_CAPTCHA.init_app(app)
app.config['SESSION_TYPE'] = 'filesystem'
app.secret_key = sk

ROUTES = ["/", "/login", "/create", "/support", "/user"]
METHODS = ["GET", "POST"]
TEMPLATES_NAME = ["base.html", "login.html", "create.html", "support.html", "user.html"]


@app.route(ROUTES[0])
def home():
    return render_template( TEMPLATES_NAME[0] )

@app.route(ROUTES[1], methods=METHODS)
def login():
    if request.method == 'GET':
        new_captcha_dict = SIMPLE_CAPTCHA.create()
        return render_template( TEMPLATES_NAME[1] , captcha=new_captcha_dict )
        
    # this is actually interesting ( it also works for POST )
    elif request.method == 'POST':
        c_hash = request.form.get('captcha-hash')
        c_text = request.form.get('captcha-text')

        # ideally we don't want to prompt the user to
        # refill every entry of the form, once they fail the captcha
        # this is food for thought

        email = request.form['inputEmail']
        password = request.form['inputPassword']
        print ( email, password )

        if SIMPLE_CAPTCHA.verify(c_text, c_hash):
            # before that we need
            # to get the info from the forms

            return 'success'
        else:

            return 'failed captcha'


@app.route(ROUTES[2], methods=METHODS)
def create():
    if request.method == METHODS[0]:
        new_captcha_dict = SIMPLE_CAPTCHA.create()
        return render_template( TEMPLATES_NAME[2], captcha=new_captcha_dict )
        
    # this is actually interesting ( it also works for POST )
    elif request.method == METHODS[1]:

        c_hash = request.form.get('captcha-hash')
        c_text = request.form.get('captcha-text')
        
        email = request.form['inputEmail']
        name  = request.form['inputUsername']
        password = request.form['p1']
        rpassword= request.form['p2']

        entity = constructEntity( name =name, email=email, passw=password)

        print( f"the entity : {entity}")

        if SIMPLE_CAPTCHA.verify(c_text, c_hash):
            # make the post request, then redirect to the user's page
            # make a post request
            content = requests.post (POST_API_URL, json = entity )
            status_code = content.status_code

            print ( status_code )
            if ( OK( status_code=status_code) ) :
                # then the post is all good
                # add the current user in the flask session 
                # store the actual name of the user in the session
                # redirect to the user page
                session['username'] = entity["name"]
                return redirect( url_for( 'user' ) )
            
            else:
                # then the post request failed
                # get the message ( cotnetn attr )
                msg = str ( content.content )
                # flash it too
                return msg
        else:
            # use the msg flasing flask
            # TODO
            return "refused"
            # # create a new captcha dict
            # new_captcha_dict = SIMPLE_CAPTCHA.create()
            # return render_template( TEMPLATES_NAME[2], captcha=new_captcha_dict )


@app.route ( ROUTES[4])
def user():
    if request.method == METHODS[0]:
        # check if the user is logged in.
        if session['username']:
            return render_template( TEMPLATES_NAME[4], name=session['username'] )

        else :
            # redirect to the login page
            # with the flash message
            msg = "dont be so slick, login first"



@app.route( ROUTES[3] )
def support():
    return render_template( TEMPLATES_NAME[3] )