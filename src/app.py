import os
from flask import Flask, render_template, request
from flask_simple_captcha import CAPTCHA

## init the captcha configs
YOUR_CONFIG = {
    'SECRET_CAPTCHA_KEY': 'LONG_KEY',
    'CAPTCHA_LENGTH': 6,
    'CAPTCHA_DIGITS': False,
    'EXPIRE_SECONDS': 600,
}
SIMPLE_CAPTCHA = CAPTCHA(config=YOUR_CONFIG)

app = Flask(__name__)
app = SIMPLE_CAPTCHA.init_app(app)

ROUTES = ["/", "/login", "/create", "/support"]
METHODS = ["GET", "POST"]
TEMPLATES_NAME = ["base.html", "login.html", "create.html", "support.html"]


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
        if SIMPLE_CAPTCHA.verify(c_text, c_hash):
            return 'success'
        else:
            return 'failed captcha'



@app.route(ROUTES[2], methods=METHODS)
def create():
    if request.method == 'GET':
        new_captcha_dict = SIMPLE_CAPTCHA.create()
        return render_template( TEMPLATES_NAME[2], captcha=new_captcha_dict )
        
    # this is actually interesting ( it also works for POST )
    elif request.method == 'POST':
        c_hash = request.form.get('captcha-hash')
        c_text = request.form.get('captcha-text')
        if SIMPLE_CAPTCHA.verify(c_text, c_hash):
            return 'success'
        else:
            return 'failed captcha'


@app.route( ROUTES[3] )
def support():
    return render_template( TEMPLATES_NAME[3] )