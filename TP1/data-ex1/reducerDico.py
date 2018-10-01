#! /usr/bin/env python
import sys

word2count = {}

for line in sys.stdin:
	line = line.strip()
	word, count = line.split(' ', 1)
	
	try:
		count = int(count)
	except ValueError:
		continue
	
	if word not in word2count:
		word2count[word] = 1
	else:
		word2count[word] += 1


for word in word2count.keys():
	print '%s\t%d' % (word, word2count[word])
