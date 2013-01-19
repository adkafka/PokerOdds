#!/bin/sh

HANDS=$1;
FILE=Output/Poker.txt

echo Simulating ${HANDS} poker hands...

cd /Users/Adam/Desktop/Projects/Poker
rm $FILE

echo "Computed on date -v1m -v+1y" >> $FILE
echo "WRITEN BY ADAM KAFKA" >> $FILE

time for ((n=1;n<=$HANDS;n++))
	do
	echo Calculating hand ${n} out of ${HANDS} 
	java HoldEm >> $FILE; 
done

say "Done Computing"

./PokerCalc.sh $FILE
