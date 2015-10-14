package mp247gui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum Bonus {
	Event {
		@Override
		protected Comparator<Player> getComparator() {
			return new ComparatorEvent();
		}
	},Minigames {

		@Override
		protected Comparator<Player> getComparator() {
			return new ComparatorMinigames();
		}
		
	},Max {
		@Override
		protected Comparator<Player> getComparator() {
			return new ComparatorMax();
		}
	};
	
	
	public Set<Player> getWinners(Player[] players){
		List<Player> in = Arrays.asList(players);
		return getWinners(in,getComparator());
		
	}
	
	protected abstract Comparator<Player> getComparator();
	
	private static Set<Player> getWinners(List<Player> in,Comparator<Player> comp){
		Collections.sort(in,comp);
		Player winner = in.get(0);
		Set<Player> ret = new HashSet<>();
		ret.add(winner);
		
		int i = 1;
		while (i < in.size() && comp.compare(in.get(i), winner) == 0){
			ret.add(in.get(i));
			i++;
		}
		
		return ret;
		
	}
	
	private class ComparatorMinigames implements Comparator<Player>{

		@Override
		public int compare(Player o1, Player o2) {
			return -1 * Integer.compare(o1.getMinigameCoins(),o2.getMinigameCoins());
		}
	}
	private class ComparatorMax implements Comparator<Player>{

		@Override
		public int compare(Player o1, Player o2) {
			return -1 * Integer.compare(o1.getMaxCoins(),o2.getMaxCoins());
		}
		
	}
		
	private class ComparatorEvent implements Comparator<Player>{

		@Override
		public int compare(Player arg0, Player arg1) {
			return -1 * Integer.compare(arg0.getEventCells(), arg1.getEventCells());
		}
	}
}
