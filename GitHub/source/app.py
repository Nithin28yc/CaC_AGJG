import os
import sqlite3
from flask import Flask, redirect, url_for, request, render_template
from pymongo import MongoClient
app = Flask(__name__)
NAMESPACE = os.environ.get("NAMESPACE")
print ("NAMESPACE in app--------",NAMESPACE)
client = MongoClient("mongodb://mongopy."+NAMESPACE+".svc.cluster.local"+":27017/fladocks")
db = client['fladocks']

# Below code commented as testcase wont run as the mongopy pod is deployed in user desired Namespace and the CI runs in jenkins-slave pod which is in ethan NS.
# client = MongoClient(
#     "mongodb://mongopy:27017/fladocks")
# db = client['fladocks']

@app.route('/')
def todo():
    _items = db.todos.find()
    items = [item for item in _items]

    return render_template('index.html', items=items)

@app.route('/test')
def todo_new():
    return render_template('index.html')


@app.route('/new', methods=['POST'])
def new():

    item_doc = {
        'name': request.form['name'],
        'description': request.form['description']
    }
    db.todos.insert_one(item_doc)

    return redirect(url_for('todo'))


if __name__ == "__main__":
    app.run(host='0.0.0.0', debug=True)
