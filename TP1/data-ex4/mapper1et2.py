#! /usr/bin/env python
import sys

lastRemeberedName = ''
for line in sys.stdin:
	line = line.strip()
	id, d, name = line.split(',')
	day, month, year = d.split('/')

	if month == '07':
		if lastRemeberedName != name:
			lastRemeberedName = name
			print '%s' % (lastRemeberedName)
			
