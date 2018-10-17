#! /usr/bin/env python
import sys

for line in sys.stdin:
	line = line.strip()
	idc, ido, total = line.split(',')

	print "%s\t%s\t%s" % (idc, total, 1)

			
