import os
from flask import Flask, render_template
app = Flask(__name__)

ROUTES = ["/", "/login", "/create", "/support"]
TEMPLATES_NAME = ["base.html", "login.html", "create.html", "support.html"]


@app.route(ROUTES[0])
def home():
    return render_template( TEMPLATES_NAME[0] )

@app.route(ROUTES[1])
def login():
    return render_template( TEMPLATES_NAME[1] )

@app.route(ROUTES[2])
def create():
    return render_template( TEMPLATES_NAME[2] )

@app.route( ROUTES[3] )
def support():
    return render_template( TEMPLATES_NAME[3] )