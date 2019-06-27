#!/usr/bin/env python

"""
Check students use the level list drawable.
Return: 0 = success, 1 = fail
"""

import xml.etree.ElementTree as ET
import sys
import os

def find_rec(root, target):
    if root is None:
        return None
    view = root.find(target)
    if view is not None:
        return view
    for x in root:
        view = find_rec(x, target)
        if view is not None:
            return view
    return None

# put this file in the root folder containing the target project
project_name = 'BatteryLevelIndicator'
res_path = './{}/app/src/main/res'.format(project_name)
layout_file = res_path + '/layout/activity_main.xml'

if not os.path.isfile(layout_file):
    print "{} does not exist.".format(layout_file)
    sys.exit(1)

# find the name drawable file containing level list
tree = ET.parse(layout_file)
root = tree.getroot()
if root is None:
    print "Can not get root of xml from {}".format(layout_file)
    sys.exit(1)
view = find_rec(root, 'ImageView')
if view is None:
    print "Can not find ImageView from {}".format(layout_file)
    sys.exit(1)
attrs = view.attrib

dname = None
for k, v in attrs.iteritems():
    if 'srcCompat' in k or 'src' in k or 'background' in k:
        dname = v
        break

if dname is None:
    print "ImageView does not contain the attribute: (srcCompat || src || background)."
    sys.exit(1)
i = dname.find('/')
if i == -1:
    print "Can not find / in {}".format(dname)
    sys.exit(1)
dname = dname[i+1:]

# the path of the file containing level list
drawable_file = res_path + '/drawable/{}.xml'.format(dname)

if not os.path.isfile(drawable_file):
    print "drawable file, {}, does not exist.".format(drawable_file)
    sys.exit(1)

# check the root's tag is level-list
tree = ET.parse(drawable_file)
root = tree.getroot()
if root.tag != 'level-list':
    print "Root type of {} is not level-list.".format(drawable_file)
    sys.exit(1)
else:
    sys.exit(0)
