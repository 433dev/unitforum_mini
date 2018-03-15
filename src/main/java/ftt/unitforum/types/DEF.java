package ftt.unitforum.types;

public final class DEF {
	public enum ARTICLE_ORDER { 
		RECOMMEND(0), LATEST(1); // 추천순, 최신순
		
		private int value;
		
		private ARTICLE_ORDER(int value){
			this.value = value;
		}
		
		public int getValue(){
			return value;
		}
		
		public static DEF.ARTICLE_ORDER getDefine(int value){
			
			for(DEF.ARTICLE_ORDER o : DEF.ARTICLE_ORDER.values()){
				if (o.getValue() == value)
					return o;
			}
						
			return DEF.ARTICLE_ORDER.RECOMMEND;
		}
	};

	public enum ARTICLE_ORDER2 { 
		RECOMMEND, LATEST;
	};

}
