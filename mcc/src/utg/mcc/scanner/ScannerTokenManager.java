/* Generated By:JavaCC: Do not edit this line. ScannerTokenManager.java */
package utg.mcc.scanner;

/** Token Manager. */
@SuppressWarnings("all")
public class ScannerTokenManager implements ScannerConstants
{

  /** Debug output. */
  public static  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public static  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private static final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x3f000L) != 0L)
         {
            jjmatchedKind = 19;
            return 2;
         }
         return -1;
      case 1:
         if ((active0 & 0x2000L) != 0L)
            return 2;
         if ((active0 & 0x3d000L) != 0L)
         {
            jjmatchedKind = 19;
            jjmatchedPos = 1;
            return 2;
         }
         return -1;
      case 2:
         if ((active0 & 0x4000L) != 0L)
            return 2;
         if ((active0 & 0x39000L) != 0L)
         {
            jjmatchedKind = 19;
            jjmatchedPos = 2;
            return 2;
         }
         return -1;
      case 3:
         if ((active0 & 0x1000L) != 0L)
            return 2;
         if ((active0 & 0x38000L) != 0L)
         {
            jjmatchedKind = 19;
            jjmatchedPos = 3;
            return 2;
         }
         return -1;
      case 4:
         if ((active0 & 0x28000L) != 0L)
            return 2;
         if ((active0 & 0x10000L) != 0L)
         {
            jjmatchedKind = 19;
            jjmatchedPos = 4;
            return 2;
         }
         return -1;
      default :
         return -1;
   }
}
private static final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
static private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
static private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 33:
         jjmatchedKind = 41;
         return jjMoveStringLiteralDfa1_0(0x400000000L);
      case 38:
         return jjMoveStringLiteralDfa1_0(0x10000000000L);
      case 40:
         return jjStopAtPos(0, 22);
      case 41:
         return jjStopAtPos(0, 23);
      case 42:
         return jjStopAtPos(0, 37);
      case 43:
         return jjStopAtPos(0, 35);
      case 44:
         return jjStopAtPos(0, 27);
      case 45:
         return jjStopAtPos(0, 36);
      case 47:
         jjmatchedKind = 38;
         return jjMoveStringLiteralDfa1_0(0xc0L);
      case 59:
         return jjStopAtPos(0, 26);
      case 60:
         jjmatchedKind = 30;
         return jjMoveStringLiteralDfa1_0(0x100000000L);
      case 61:
         jjmatchedKind = 28;
         return jjMoveStringLiteralDfa1_0(0x80000000L);
      case 62:
         jjmatchedKind = 29;
         return jjMoveStringLiteralDfa1_0(0x200000000L);
      case 101:
         return jjMoveStringLiteralDfa1_0(0x1000L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x6000L);
      case 112:
         return jjMoveStringLiteralDfa1_0(0x20000L);
      case 114:
         return jjMoveStringLiteralDfa1_0(0x10000L);
      case 119:
         return jjMoveStringLiteralDfa1_0(0x8000L);
      case 123:
         return jjStopAtPos(0, 24);
      case 124:
         return jjMoveStringLiteralDfa1_0(0x8000000000L);
      case 125:
         return jjStopAtPos(0, 25);
      default :
         return jjMoveNfa_0(1, 0);
   }
}
static private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 38:
         if ((active0 & 0x10000000000L) != 0L)
            return jjStopAtPos(1, 40);
         break;
      case 42:
         if ((active0 & 0x80L) != 0L)
            return jjStopAtPos(1, 7);
         break;
      case 47:
         if ((active0 & 0x40L) != 0L)
            return jjStopAtPos(1, 6);
         break;
      case 61:
         if ((active0 & 0x80000000L) != 0L)
            return jjStopAtPos(1, 31);
         else if ((active0 & 0x100000000L) != 0L)
            return jjStopAtPos(1, 32);
         else if ((active0 & 0x200000000L) != 0L)
            return jjStopAtPos(1, 33);
         else if ((active0 & 0x400000000L) != 0L)
            return jjStopAtPos(1, 34);
         break;
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x10000L);
      case 102:
         if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_0(1, 13, 2);
         break;
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x8000L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x1000L);
      case 110:
         return jjMoveStringLiteralDfa2_0(active0, 0x4000L);
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x20000L);
      case 124:
         if ((active0 & 0x8000000000L) != 0L)
            return jjStopAtPos(1, 39);
         break;
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
static private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x28000L);
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000L);
      case 116:
         if ((active0 & 0x4000L) != 0L)
            return jjStartNfaWithStates_0(2, 14, 2);
         return jjMoveStringLiteralDfa3_0(active0, 0x10000L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
static private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_0(3, 12, 2);
         break;
      case 108:
         return jjMoveStringLiteralDfa4_0(active0, 0x8000L);
      case 110:
         return jjMoveStringLiteralDfa4_0(active0, 0x20000L);
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x10000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
static private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_0(4, 15, 2);
         break;
      case 114:
         return jjMoveStringLiteralDfa5_0(active0, 0x10000L);
      case 116:
         if ((active0 & 0x20000L) != 0L)
            return jjStartNfaWithStates_0(4, 17, 2);
         break;
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
static private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 110:
         if ((active0 & 0x10000L) != 0L)
            return jjStartNfaWithStates_0(5, 16, 2);
         break;
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
static private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 3;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
               case 0:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 18)
                     kind = 18;
                  jjCheckNAdd(0);
                  break;
               case 2:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 19)
                     kind = 19;
                  jjstateSet[jjnewStateCnt++] = 2;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
               case 2:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 19)
                     kind = 19;
                  jjCheckNAdd(2);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 3 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static private int jjMoveStringLiteralDfa0_3()
{
   switch(curChar)
   {
      case 42:
         return jjMoveStringLiteralDfa1_3(0x400L);
      default :
         return 1;
   }
}
static private int jjMoveStringLiteralDfa1_3(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 1;
   }
   switch(curChar)
   {
      case 47:
         if ((active0 & 0x400L) != 0L)
            return jjStopAtPos(1, 10);
         break;
      default :
         return 2;
   }
   return 2;
}
static private int jjMoveStringLiteralDfa0_1()
{
   return jjMoveNfa_1(0, 0);
}
static private int jjMoveNfa_1(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 3;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x2400L & l) != 0L)
                  {
                     if (kind > 8)
                        kind = 8;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 1:
                  if (curChar == 10 && kind > 8)
                     kind = 8;
                  break;
               case 2:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 3 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static private int jjMoveStringLiteralDfa0_2()
{
   switch(curChar)
   {
      case 42:
         return jjMoveStringLiteralDfa1_2(0x200L);
      default :
         return 1;
   }
}
static private int jjMoveStringLiteralDfa1_2(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 1;
   }
   switch(curChar)
   {
      case 47:
         if ((active0 & 0x200L) != 0L)
            return jjStopAtPos(1, 9);
         break;
      default :
         return 2;
   }
   return 2;
}
static final int[] jjnextStates = {
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, null, null, null, 
"\145\154\163\145", "\151\146", "\151\156\164", "\167\150\151\154\145", 
"\162\145\164\165\162\156", "\160\162\151\156\164", null, null, null, null, "\50", "\51", "\173", "\175", 
"\73", "\54", "\75", "\76", "\74", "\75\75", "\74\75", "\76\75", "\41\75", "\53", 
"\55", "\52", "\57", "\174\174", "\46\46", "\41", };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
   "IN_SINGLE_LINE_COMMENT",
   "IN_FORMAL_COMMENT",
   "IN_MULTI_LINE_COMMENT",
};

/** Lex State array. */
public static final int[] jjnewLexState = {
   -1, -1, -1, -1, -1, -1, 1, 3, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
};
static final long[] jjtoToken = {
   0x3ffffcff001L, 
};
static final long[] jjtoSkip = {
   0xffeL, 
};
static protected SimpleCharStream input_stream;
static private final int[] jjrounds = new int[3];
static private final int[] jjstateSet = new int[6];
static protected char curChar;
/** Constructor. */
public ScannerTokenManager(SimpleCharStream stream){
   if (input_stream != null)
      throw new TokenMgrError("ERROR: Second call to constructor of static lexer. You must use ReInit() to initialize the static variables.", TokenMgrError.STATIC_LEXER_ERROR);
   input_stream = stream;
}

/** Constructor. */
public ScannerTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
static public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
static private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 3; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
static public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
static public void SwitchTo(int lexState)
{
   if (lexState >= 4 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

static protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

static int curLexState = 0;
static int defaultLexState = 0;
static int jjnewStateCnt;
static int jjround;
static int jjmatchedPos;
static int jjmatchedKind;

/** Get the next Token. */
public static Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   switch(curLexState)
   {
     case 0:
       try { input_stream.backup(0);
          while (curChar <= 32 && (0x100003600L & (1L << curChar)) != 0L)
             curChar = input_stream.BeginToken();
       }
       catch (java.io.IOException e1) { continue EOFLoop; }
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_0();
       break;
     case 1:
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_1();
       if (jjmatchedPos == 0 && jjmatchedKind > 11)
       {
          jjmatchedKind = 11;
       }
       break;
     case 2:
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_2();
       break;
     case 3:
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_3();
       if (jjmatchedPos == 0 && jjmatchedKind > 11)
       {
          jjmatchedKind = 11;
       }
       break;
   }
     if (jjmatchedKind != 0x7fffffff)
     {
        if (jjmatchedPos + 1 < curPos)
           input_stream.backup(curPos - jjmatchedPos - 1);
        if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           matchedToken = jjFillToken();
       if (jjnewLexState[jjmatchedKind] != -1)
         curLexState = jjnewLexState[jjmatchedKind];
           return matchedToken;
        }
        else
        {
         if (jjnewLexState[jjmatchedKind] != -1)
           curLexState = jjnewLexState[jjmatchedKind];
           continue EOFLoop;
        }
     }
     int error_line = input_stream.getEndLine();
     int error_column = input_stream.getEndColumn();
     String error_after = null;
     boolean EOFSeen = false;
     try { input_stream.readChar(); input_stream.backup(1); }
     catch (java.io.IOException e1) {
        EOFSeen = true;
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
        if (curChar == '\n' || curChar == '\r') {
           error_line++;
           error_column = 0;
        }
        else
           error_column++;
     }
     if (!EOFSeen) {
        input_stream.backup(1);
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
     }
     throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

static private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
static private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
static private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

}
