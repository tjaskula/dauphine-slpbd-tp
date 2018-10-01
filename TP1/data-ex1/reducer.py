#! /usr/bin/env python
import sys

currentWord = ''
currentCount = 0
for wordCount in sys.stdin:
	#print '%s' % wordCount
	word, count = wordCount.split(' ', 1)
	if currentWord != word:
		print '%s - %d' % (currentWord, currentCount)
		currentWord = word
		currentCount = int(count)
	else:
		currentCount = currentCount + 1
