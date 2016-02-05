#!/usr/bin/python

import sys
import os

#Generate maven commands to bootstrap application
arguments = sys.argv
arguments.pop(0)
sArguments = ' '.join(arguments)
sCmd = "mvn spring-boot:run -q -Drun.arguments='" + sArguments + "'"

#Start application
os.system(sCmd)



