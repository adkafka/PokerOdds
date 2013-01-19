import cs1.*;
public class HoldEm
{
    public static void output_deck(int deck[][]){//Ouputs the Deck of Cards
        for(int count=0;count<=51;count++){
            System.out.println(Card(deck,count));
        }
    }
    public static void output_hand(int deck[][]){//Ouputs the Deck of Cards
        for(int count=0;count<=6;count++){
            System.out.println(Card(deck,count));
        }
    }
    public static String Card(int deck[][], int cardnum){//Translates arrays for deck into english
        int value=deck[cardnum][0];
        int suit=deck[cardnum][1];
        String card;
        switch(value){
            case -1: card="Ace (low) of";
            break;
            case 0: card="Two of";
            break;
            case 1: card="Three of";
            break;
            case 2: card="Four of";
            break;
            case 3: card="Five of";
            break;
            case 4: card="Six of";
            break;
            case 5: card="Seven of";
            break;
            case 6: card="Eight of";
            break;
            case 7: card="Nine of";
            break;
            case 8: card="Ten of";
            break;
            case 9: card="Jack of";
            break;
            case 10: card="Queen of";
            break;
            case 11: card="King of";
            break;
            case 12: card="Ace of";
            break;
            default: card="Not a card";
            break;
        }
        switch(suit){
            case 0: card=card+" Spades";
            break;
            case 1: card=card+" Clubs";
            break;
            case 2: card=card+" Diamonds";
            break;
            case 3: card=card+" Hearts";
            break;
            default: card=card+" Not a suit";
            break;
        }
        return card;
    }
    public static int shuffle(int deck[][]){//shuffles deck
        int index=0;
        int pos1;
        int pos2;
        int card1;
        int suit1;
        int card2;
        int suit2;
        for(int suit=0;suit<4;suit++){//start with new unshuffled deck
            for(int card=0;card<13;card++){
                deck[index][0]=card;
                deck[index][1]=suit;
                index++;
                //System.out.print("Suit="+suit+"\t"+"Card="+card+"\t"+"Index="+index);
                //System.out.println();
            }
        }
        for(int count=0;count<1000;count++){//Preforms 1000 card switches in the deck
            pos1=(int)(52*Math.random());
            suit1=deck[pos1][1];
            card1=deck[pos1][0];
            pos2=(int)(52*Math.random());
            suit2=deck[pos2][1];
            card2=deck[pos2][0];
            
            deck[pos1][0]=card2;
            deck[pos1][1]=suit2;
            deck[pos2][0]=card1;
            deck[pos2][1]=suit1;
        }
        
        /*for(int count=0;count<=51;count++){ just makes 0-51
            deck[count][0]=count;
            System.out.println(count);
        }*/
        return 0;
    }
    public static int your_hand(int deck[][],int hand[][]){//Assuming there are "Players" playing
        int players=4;
        
        //deal
        hand[0][0]=deck[0][0];//Adds fist card that player is dealt
        hand[0][1]=deck[0][1];//Add suit of first card
        hand[1][0]=deck[players][0];//add second card
        hand[1][1]=deck[players][1];//second suit
        
        //flop
        hand[2][0]=deck[2*players+1][0];
        hand[2][1]=deck[2*players+1][1];
        hand[3][0]=deck[2*players+2][0];
        hand[3][1]=deck[2*players+2][1];
        hand[4][0]=deck[2*players+3][0];
        hand[4][1]=deck[2*players+3][1];
        //turn
        hand[5][0]=deck[2*players+5][0];
        hand[5][1]=deck[2*players+5][1];
        //river
        hand[6][0]=deck[2*players+7][0];
        hand[6][1]=deck[2*players+7][1];
        
        return 0;
    }
    public static int sort_hand(int hand[][]){//Sorts hand to make computing which hand the player has easier
        //Variables for sort
        int Value;//Selected Card Value
        int CardSlot=0;//Saves spot of minimum card to switch can happen succesfully
        int CardMin;//Minumum value from query
        int CompCard;//Comparison card
        int Slot;//Used for comparison card
        
        //Variables for switch
        int pos1;
        int pos2;
        int card1;
        int card2;
        int suit1;
        int suit2;
        for(int run=0;run<=5;run++){//Selects a primary card
            Value=hand[run][0];
            CardMin=Value;
            Slot=run;
            do{
                Slot=Slot+1;
                CompCard=hand[Slot][0];
                if(CompCard<CardMin){//Tests if the primary card is greater than the comparison card
                    CardMin=hand[Slot][0];
                    CardSlot=Slot;
                }
            }while(Slot<6);
            if(CardMin<Value){//if the minimum found is not the primary card, a card switch will occur
                //perform card switch with primary and comparison card
                pos1=run;
                suit1=hand[pos1][1];
                card1=hand[pos1][0];
                pos2=CardSlot;
                suit2=hand[pos2][1];
                card2=hand[pos2][0];
                
                hand[pos1][0]=card2;
                hand[pos1][1]=suit2;
                hand[pos2][0]=card1;
                hand[pos2][1]=suit1;
                
                //System.out.println("Switch made with card "+pos1+" and "+pos2);
                //output_hand(hand);
            }
        }
        return 0;
    }
    //Detection of hands
    public static void detect(int hand[][]){//Ouputs if it is a straight
        if (RoyalFlush(hand)){
            System.out.println("You have a ROYAL FLUSH!");
        }
        else if(StraightFlush(hand)){
            System.out.println("You have a Straight Flush");
        }
        else if(Quads(hand)){
            System.out.println("You have four of a kind");
        }
        else if(FullHouse(hand)){
            System.out.println("You have a full house");
        }
        else if(Flush(hand)){
            System.out.println("You have a flush");
        }
        else if(Straight(hand)){
            System.out.println("You have a Straight");
        }
        else if(Trips(hand)){
            System.out.println("You have 3 of a kind");
        }
        else if(TwoPair(hand)){
            System.out.println("You have two pair");
        }
        else if(OnePair(hand)){
            System.out.println("You have one pair");
        }else{
            System.out.println("You have a high card, the "+Card(hand,6));
        }
    }
    public static boolean RoyalFlush(int hand[][]){//Creates boolean value for a Royal FLush
        boolean royalflush=false;
        //output_hand(hand);
        boolean straightflush=false;
        int StartSlot;
        int NextSlot;
        int PreviousSlot;//This is the reference slot
        int Straight=0;//must be 4 or above to be a straight
        int FinalStart=0;
        for(int Suit=0;Suit<=3;Suit++){//Run thru each suit
            //System.out.println("Checking Suit "+Suit);
            StartSlot=0;//Where the straight starts
            NextSlot=StartSlot;
            PreviousSlot=0;
            Straight=0;
            do{
                if(hand[NextSlot][1]==Suit){//If card is same suit as Suit
                    if(Straight==0){//If begins straight
                        //System.out.println("Starting straight at "+NextSlot);
                        StartSlot=NextSlot;
                        PreviousSlot=NextSlot;
                        NextSlot++;
                        Straight++;
                    }
                    else if(hand[PreviousSlot][0]+1==hand[NextSlot][0]){//Card is next in order
                        //System.out.println("Continueing straight at "+NextSlot);
                        Straight++;
                        if(Straight>=5){
                            straightflush=true;
                            FinalStart=StartSlot;
                            System.out.println("Straight Flush found starting with "+Card(hand,StartSlot));
                        }
                        PreviousSlot=NextSlot;
                        NextSlot++;
                    }
                    else if(hand[StartSlot][0]==0 && Straight==3){//For the case that straight starts at ace low (already has 2 thru 5 and no 6)
                        if(hand[4][0]==12 && hand[4][1]==Suit){
                            Straight++;
                            System.out.println("Straight Flush found starting with "+Card(hand,4)+" (low)");
                            if(Straight>=5){
                                straightflush=true;
                                FinalStart=StartSlot;
                            }
                        }
                        else if(hand[5][0]==12 && hand[5][1]==Suit){
                            Straight++;
                            System.out.println("Straight Flush found starting with "+Card(hand,5)+" (low)");
                            if(Straight>=5){
                                straightflush=true;
                                FinalStart=StartSlot;
                            }
                        }
                        else if(hand[6][0]==12 && hand[6][1]==Suit){
                            Straight++;
                            System.out.println("Straight Flush found starting with "+Card(hand,6)+" (low)");
                            if(Straight>=5){
                                straightflush=true;
                                FinalStart=StartSlot;
                            }
                        }
                        else{
                            //System.out.println("Straight broke at "+NextSlot+" on ace check");
                            Straight=0;
                            NextSlot++;
                        }
                    }
                    else{//broke straight
                        //System.out.println("Broke straight at "+NextSlot);
                        Straight=0;
                        NextSlot++;
                    }
                }
                else{//skip this card and continue with the next one
                    NextSlot++;
                    //System.out.println("Ignoring card at "+NextSlot);
                }
            }while(NextSlot<=6);
        }
        if(straightflush==true && hand[FinalStart][0]==8){//Check if straight flush starts with card value of 10 (8 in array system)
            royalflush=true;
            System.out.println("Royal Flush detected starting at "+Card(hand,FinalStart));
        }
        return royalflush;
    }
    public static boolean StraightFlush(int hand[][]){//Creates boolean value for Straight Flush
        //output_hand(hand);
        boolean straightflush=false;
        int StartSlot;
        int NextSlot;
        int PreviousSlot;//This is the reference slot
        int Straight=0;//must be 4 or above to be a straight
        for(int Suit=0;Suit<=3;Suit++){//Run thru each suit
            //System.out.println("Checking Suit "+Suit);
            StartSlot=0;//Where the straight starts
            NextSlot=StartSlot;
            PreviousSlot=0;
            Straight=0;
            do{
                if(hand[NextSlot][1]==Suit){//If card is same suit as Suit
                    if(Straight==0){//If begins straight
                        //System.out.println("Starting straight at "+NextSlot);
                        StartSlot=NextSlot;
                        PreviousSlot=NextSlot;
                        NextSlot++;
                        Straight++;
                    }
                    else if(hand[PreviousSlot][0]+1==hand[NextSlot][0]){//Card is next in order
                        //System.out.println("Continueing straight at "+NextSlot);
                        PreviousSlot=NextSlot;
                        NextSlot++;
                        Straight++;
                        if(Straight>=5){
                            straightflush=true;
                            System.out.println("Straight Flush found starting with "+Card(hand,StartSlot));
                        }
                    }
                    else if(hand[StartSlot][0]==0 && Straight==3){//For the case that straight starts at ace low (already has 2 thru 5 and no 6)
                        if(hand[4][0]==12 && hand[4][1]==Suit){
                            Straight++;
                            System.out.println("Straight Flush found starting with "+Card(hand,4)+" (low)");
                            if(Straight>=5){
                                straightflush=true;
                            }
                        }
                        else if(hand[5][0]==12 && hand[5][1]==Suit){
                            Straight++;
                            System.out.println("Straight Flush found starting with "+Card(hand,5)+" (low)");
                            if(Straight>=5){
                                straightflush=true;
                            }
                        }
                        else if(hand[6][0]==12 && hand[6][1]==Suit){
                            Straight++;
                            System.out.println("Straight Flush found starting with "+Card(hand,6)+" (low)");
                            if(Straight>=5){
                                straightflush=true;
                            }
                        }
                        else{
                            //System.out.println("Straight broke at "+NextSlot+" on ace check");
                            Straight=0;
                            NextSlot++;
                        }
                    }
                    else{//broke straight
                        //System.out.println("Broke straight at "+NextSlot);
                        Straight=0;
                        NextSlot++;
                    }
                }
                else{//skip this card and continue with the next one
                    NextSlot++;
                    //System.out.println("Ignoring card at "+NextSlot);
                }
            }while(NextSlot<=6);
        }
        return straightflush;
    }
       public static boolean Quads(int hand[][]){//Creates boolean value for a Four of a Kind
        boolean trip = false;
        int Slot=0;
        for(int testnum=0;testnum<4;testnum++){
            Slot=testnum+1;
            do{
                if(hand[Slot][0]==hand[testnum][0] && testnum!=Slot){//If equal value, and not same card
                    for(int Slot2=Slot+1;Slot2<=6;Slot2++){
                        if(hand[Slot2][0]==hand[Slot][0]){
                           for(int Slot3=Slot2+1;Slot3<=6;Slot3++){
                                if(hand[Slot3][0]==hand[Slot][0]){
                                    trip = true;
                                    System.out.println("Four of a kind of "+Card(hand,testnum)+", "+Card(hand,Slot)+", "+Card(hand,Slot2)+"and "+Card(hand,Slot3));
                           
                                }
                            }
                        }
                    }
                }
                Slot=Slot+1;
            }while(Slot<6 && trip==false);
        }
        return trip;
    }
   public static boolean FullHouse(int hand[][]){//Creates boolean value for a Full House
        boolean fullhouse = false;
        //Check if there is a three of a kind
        boolean trip = false;
        boolean Pair = false;
        int Slot=0;
        int TripCard=0;//What card has the triplet - to prevent duplication
        String Over="";//three of a kind value
        String Under="";//pair value
        int OverEnd=0;//First word of card
        int UnderEnd=0;//First word of card
        for(int testnum=0;testnum<5;testnum++){
            Slot=testnum+1;
            do{
                if(hand[Slot][0]==hand[testnum][0] && testnum!=Slot){//If equal value, and not same card
                    for(int Slot2=Slot+1;Slot2<=6;Slot2++){
                        if(hand[Slot2][0]==hand[Slot][0]){
                            trip = true;
                            System.out.println("Three of a kind of "+Card(hand,testnum)+", "+Card(hand,Slot)+" and "+Card(hand,Slot2));
                            //Create string for card number
                            Over=Card(hand,testnum);
                            OverEnd=Over.indexOf(" ");
                            Over=Over.substring(0,OverEnd);
                            TripCard=testnum;
                        }
                    }
                }
                Slot=Slot+1;
            }while(Slot<6 && trip==false);
        }
        if(trip==true){//Check for pair neglecting the three of a kind
            int Slot2=0;
            for(int testnum2=0;testnum2<=5;testnum2++){
                Slot2=testnum2+1;
                do{
                    if(hand[Slot2][0]==hand[testnum2][0] && testnum2!=Slot && hand[testnum2][0]!=hand[TripCard][0]){//If equal value, not same card, and not the trip
                        Pair=true;
                        System.out.println("Pair of "+Card(hand,Slot2)+" and "+Card(hand,testnum2));
                        //Create string for card number
                        Under=Card(hand,testnum2);
                        UnderEnd=Under.indexOf(" ");
                        Under=Under.substring(0,UnderEnd);
                    }
                    Slot2=Slot2+1;
                }while(Slot<6 && Pair==false);
            }
        }
        if(trip==true && Pair==true){
            fullhouse=true;
            System.out.println("Full House, "+Over+"'s over "+Under+"'s");
        }        
        return fullhouse;
    } 
   public static boolean Flush(int hand[][]){//Creates boolean value for a Flush
        boolean flush = false;
        int Slot=0;
        int FlushVal;//How many cards of one suit
        for(int Suit=0;Suit<=3;Suit++){
            //System.out.println("Suit= "+Suit);
            FlushVal=0;
            Slot=0;
            do{
                //System.out.println("Slot= "+Slot);
                if(hand[Slot][1]==Suit){
                    FlushVal++;
                    //System.out.println("Found "+Card(hand,Slot)+" FlushVal= "+FlushVal);
                    if(FlushVal>=5){
                        flush=true;
                        System.out.println("Flush found with high card "+Card(hand,Slot));
                    }  
                }
                Slot++;
            }while(Slot<=6);
        }
        return flush;
    }
    public static boolean Straight(int hand[][]){//Creates boolean value for a Straight
        boolean straight=false;
        int StartValue;
        int PreviousValue;
        int NextValue;
        int Straight=0;//How many cards in a row (must be 5 for a straight)
        int Slot=0;
        Straight=0;
        //System.out.println("Beginning Slot = "+Slot);
        StartValue=hand[Slot][0];
        int StartSlot=Slot;
        PreviousValue=StartValue;
        int Slot2=Slot+1;
        do{
            //System.out.println("----Testing Slot = "+Slot2);
            NextValue=hand[Slot2][0];
            if(PreviousValue+1==NextValue){//Ascending order
                Straight++;
                if(Straight==1){//Set new start value
                    StartValue=PreviousValue;
                    StartSlot=Slot2-1;
                }
                //System.out.println("Straight= "+Straight);
                if(Straight>=4){
                    straight=true;
                    //System.out.println("Here is where straight was found (end)");
                    System.out.println("Straight found starting with "+ Card(hand,StartSlot));
                }
            }
            else if(PreviousValue==NextValue){//Same card to aviod pairs breaking the straight
                //
            }
            else if(StartValue==0 && Straight==3){//For the case that straight starts at ace low (already has 2 thru 5 and no 6)
                if(hand[6][0]==12){//there is an ace in the hand and at this point, 2 thru 5
                    Slot2=7;//to escape the rest of the loop
                    straight=true;
                    System.out.println("Straight found starting with Ace (low)");
                    Straight=0;
                    
                }
                else{
                    Straight=0;
                }
            }
            else{//Broke the straight
                Straight=0;
                //System.out.println("Straight= "+Straight);
            }
            Slot2++;
            PreviousValue=NextValue;
        }while(Slot2<=6);
        return straight;
    }
    public static boolean Trips(int hand[][]){//Creates boolean value for a three of a kind
        boolean trip = false;
        int Slot=0;
        for(int testnum=0;testnum<5;testnum++){
            Slot=testnum+1;
            do{
                if(hand[Slot][0]==hand[testnum][0] && testnum!=Slot){//If equal value, and not same card
                    for(int Slot2=Slot+1;Slot2<=6;Slot2++){
                        if(hand[Slot2][0]==hand[Slot][0]){
                            trip = true;
                            System.out.println("Three of a kind of "+Card(hand,testnum)+", "+Card(hand,Slot)+" and "+Card(hand,Slot2));
                        }
                    }
                }
                Slot=Slot+1;
            }while(Slot<6 && trip==false);
        }
        return trip;
    }
    public static boolean TwoPair(int hand[][]){//Creates boolean value for two pair
        boolean twoPair = false;
        int Slot=0;
        int pairs=0;
        for(int testnum=0;testnum<=5;testnum++){
            Slot=testnum+1;
            do{
                if(hand[Slot][0]==hand[testnum][0] && testnum!=Slot){//If equal value, and not same card
                    pairs++;
                    System.out.println("Two Pair test: Pair of "+Card(hand,Slot)+" and "+Card(hand,testnum));
                    //System.out.println("Should be true, it is... "+Pair);
                }
                Slot=Slot+1;
            }while(Slot<6);
            if(pairs==2){
                twoPair=true;
            }
            
        }
        return twoPair;
     }
    public static boolean OnePair(int hand[][]){//Creates boolean value for a single pair
        boolean Pair = false;
        int Slot=0;
        for(int testnum=0;testnum<=5;testnum++){
            Slot=testnum+1;
            do{
                if(hand[Slot][0]==hand[testnum][0] && testnum!=Slot && hand[Slot][0]!=-1){//If equal value, not same card, and not already a pair
                    Pair=true;
                    System.out.println("Pair of "+Card(hand,Slot)+" and "+Card(hand,testnum));
                    //System.out.println("Should be true, it is... "+Pair);
                }
                Slot=Slot+1;
            }while(Slot<6 && Pair==false);
        }
        return Pair;
    }
    
    public static void main(String args []){
        int deck [][]= new int [52] [2]; //ordered with first row being card value (0 for ace) [card]x[suit]
        int hand [][]=new int [7][2];
        shuffle(deck);//Gets a new shuffled deck of cards
        //output_deck(deck);//Outputs the deck of cards in plain english
        
        your_hand(deck,hand);//Deals out your hand (all 7 cards, 2 dealt, 5 on table)
        //output_hand(hand);//Ouputs your hand - all 7 cards
        
        System.out.println("-----------------------------------------------");
        
        sort_hand(hand);
        
        System.out.println("Your SORTED Hand=");
        output_hand(hand);
        
        detect(hand);
    }
}