#! /usr/bin/env python
import sys

lastRemeberedIdc = None
totalSum = 0
totalCount = 0
for line in sys.stdin:
	line = line.strip()
	idc, total, count = line.split('\t')
	total = int(total)
	count = int(count)

	if idc != lastRemeberedIdc:
		if lastRemeberedIdc != None:
			print "Customer: %s | Sum: %s | Count: %s" % (lastRemeberedIdc, totalSum, totalCount)
		lastRemeberedIdc = idc
		totalSum = total
		totalCount = count
	else:
		totalSum += int(totalSum)
		totalCount += int(count)
			
