#! /usr/bin/env python
import sys

remeberIdc = None
for line in sys.stdin:
	line = line.strip()
	idc, d, name = line.split(',')
	
	if idc == remeberIdc:	
		print "%s\t%s\t%s" % (idc, d, name)

	if remeberIdc != idc and name.startswith('G'):
		remeberIdc = idc

			
