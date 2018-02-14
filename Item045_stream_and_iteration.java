//Implement Cartesian product
//by for loops OR Stream

package effective_java;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.*;

enum Suit 
{
  CLUBS("C"), DIAMONDS("D"), HEARTS("H"), SPADES("S");
  
  private final String shortName;  
  Suit (String n) {
    this.shortName = n;
  }
  
  @Override 
  public String toString()
  {
    return shortName;
  }  
};

enum Rank 
{
  DEUCE("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"),
  SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"), JACK("J"),
  QUEEN("Q"), KING("K"), ACE("A");
  
  private final String shortName;
  Rank (String n) {
    this.shortName = n;
  } 
  
  @Override
  public String toString()
  {
    return shortName;
  } 
};



class Card {
  private Suit suit;
  private Rank rank;
  
  public Card (Suit s, Rank r) {
    this.suit = s;
    this.rank = r;    
  } 
  
  //Iterative Cartesian product computation
  public static List<Card> newDeskByForLoops() 
  {
    List<Card> result = new ArrayList<>();
    for (Suit suit : Suit.values()) {
      for (Rank rank : Rank.values()) {
        result.add(new Card(suit, rank));
      }
    } 
    return result;
  }
  
  //Cartesian product by Stream
  public static List<Card> newDeskByStream()
  {
    //Stream<Suit> suitStream = Stream.of(Suit.values());
    //Stream<Rank> rankStream = Stream.of(Rank.values());
    
    return Stream.of(Suit.values())
                 .flatMap
                 (suit->
                  Stream.of(Rank.values()).map(rank->new Card(suit, rank))
                 )
                 .collect(Collectors.toList());                
  }
  
  @Override
  public String toString() {
    return suit.toString() + "-" + rank.toString();
  }
}


public class Item045_stream_and_iteration
{


  public static void main(String args[]) 
  {
    System.out.println("Cartesian product by for Loops:");
    System.out.println(Card.newDeskByForLoops()); 
    System.out.println("Cartesian product by Stream:");
    System.out.println(Card.newDeskByStream());
  }
}