#! /usr/bin/env python
import sys

count = 1.0
lastSum = 0.0
lastUrl = ''

for line in sys.stdin:
	line = line.strip()
	url, nbVisit = line.split('\t', 1)

	if lastUrl != url:
		if lastSum > 0.0:
			print '%s\t%f' % (lastUrl, lastSum/count)
		lastUrl = url
		lastSum = float(nbVisit)
		count = 1
	else:
		lastSum += float(nbVisit)
		count += 1

print '%s\t%f' % (lastUrl, lastSum/count)
