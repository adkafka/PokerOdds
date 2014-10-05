#!/bin/sh

FILE=$1

echo "Populating Table......."

#Calculate and ouput how many of each hands were populated
#Raw Numbers
RF=`cat $FILE | grep "You have a ROYAL FLUSH!" | wc -l`
SF=`cat $FILE | grep "You have a Straight Flush" | wc -l`
FK=`cat $FILE | grep "You have four of a kind" | wc -l`
FH=`cat $FILE | grep "You have a full house" | wc -l`
FL=`cat $FILE | grep "You have a flush" | wc -l`
ST=`cat $FILE | grep "You have a Straight" | wc -l`
TK=`cat $FILE | grep "You have 3 of a kind" | wc -l`
TP=`cat $FILE | grep "You have two pair" | wc -l`
OP=`cat $FILE | grep "You have one pair" | wc -l`
HC=`cat $FILE | grep "You have a high card" | wc -l`
TOT=`cat $FILE | grep "You have " | wc -l`

#Percentages
RFperc=`echo "scale=4; $RF*100/$TOT" | bc`
SFperc=`echo "scale=4; $SF*100/$TOT" | bc`
FKperc=`echo "scale=4; $FK*100/$TOT" | bc`
FHperc=`echo "scale=4; $FH*100/$TOT" | bc`
FLperc=`echo "scale=4; $FL*100/$TOT" | bc`
STperc=`echo "scale=4; $ST*100/$TOT" | bc`
TKperc=`echo "scale=4; $TK*100/$TOT" | bc`
TPperc=`echo "scale=4; $TP*100/$TOT" | bc`
OPperc=`echo "scale=4; $OP*100/$TOT" | bc`
HCperc=`echo "scale=4; $HC*100/$TOT" | bc`

#Actual Output
echo "+----------------+----------+------------+" 
echo "| HANDS          | FREQUENCY| PERCENTAGE |"
echo "+----------------+----------+------------+" 
echo "| ROYAL FLUSH    | $RF | $RFperc% \t |"
echo "| STRAIGHT FLUSH | $SF | $SFperc% \t |"
echo "| QUADS          | $FK | $FKperc% \t |"
echo "| FULL HOUSE     | $FH | $FHperc% \t |"
echo "| FLUSH          | $FL | $FLperc% \t |"
echo "| STRAIGHT       | $ST | $STperc% \t |"
echo "| TRIPS          | $TK | $TKperc% \t |"
echo "| TWO PAIR       | $TP | $TPperc% \t |"
echo "| ONE PAIR       | $OP | $OPperc% \t |"
echo "| HIGH CARD      | $HC | $HCperc% \t |"
echo "+----------------+----------+------------+"  
echo "| TOTAL HANDS    | $TOT |            |"
echo "+----------------+----------+------------+"  

