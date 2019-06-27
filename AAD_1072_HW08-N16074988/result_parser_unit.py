#!/usr/bin/env python
from HTMLParser import HTMLParser
import sys

if len(sys.argv) != 2:
    print "Error of parameters"
    sys.exit(1)
FILEPATH=sys.argv[1]

# create a subclass and override the handler methods
class MyHTMLParser(HTMLParser):
    def __init__(self):
        HTMLParser.__init__(self)

        # 1: success, 2: failures
        self.flag = 0
        self.results = {}

    def handle_starttag(self, tag, attrs):
        if tag == "td":
            for k, v in attrs:
                if k == "class" and v == "success":
                    self.flag = 1
                if k == "class" and v == "failures":
                    self.flag = 2

    def handle_endtag(self, tag):
        pass

    def handle_data(self, data):
        if data == "\n":
            return
        if data == "failed" or data == "passed":
            self.flag = 0
            return
        if self.flag > 0:
            self.results[data] = True if self.flag == 1 else False
            self.flag = 0

parser = MyHTMLParser()

def show_result(res):
    for k, v in res.iteritems():
        print "{}  {} ".format(k, "Success" if v else "Failed")

with open(FILEPATH, 'r') as infile:
    data = str(infile.read())
    parser.feed(data)
    show_result(parser.results)
