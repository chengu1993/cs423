#!/usr/bin/env python

import re
from sys import argv
import subprocess

w = subprocess.check_output("./sleeping_ta")

res = re.match("(?:\s*(?:Helping student for \d+ seconds waiting students\s=\s*\d+|Student \d+ takes a seat waiting students\s*=\s*\d+|Student \d+ will try (again)? later))*$", w)

if res is not None:
	print "Good"
else:
	print "Bad"
