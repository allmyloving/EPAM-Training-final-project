package ua.nure.serdyuk.SummaryTask4.entity.bean;

import java.util.Comparator;

import ua.nure.serdyuk.SummaryTask4.constants.Const;

public enum TrainBeanComparator {

	DEFAULT {

		@Override
		public Comparator<TrainBean> getComparator(final String order) {
			return new Comparator<TrainBean>() {
				@Override
				public int compare(TrainBean o1, TrainBean o2) {
					if (Const.DESC.equalsIgnoreCase(order)) {
						return o2.getDepDate().compareTo(o1.getDepDate());
					}
					return o1.getDepDate().compareTo(o2.getDepDate());
				}
			};
		}

	},
	DURATION {

		@Override
		public Comparator<TrainBean> getComparator(final String order) {
			return new Comparator<TrainBean>() {
				@Override
				public int compare(TrainBean o1, TrainBean o2) {
					if (Const.DESC.equalsIgnoreCase(order)) {
						return o2.getDuration().compareTo(o1.getDuration());
					}
					return o1.getDuration().compareTo(o2.getDuration());
				}
			};
		}

	},

	TRAIN_TAG {

		@Override
		public Comparator<TrainBean> getComparator(final String order) {
			return new Comparator<TrainBean>() {
				@Override
				public int compare(TrainBean o1, TrainBean o2) {
					if (Const.DESC.equalsIgnoreCase(order)) {
						return o2.getTrainTag().compareTo(o1.getTrainTag());
					}
					return o1.getTrainTag().compareTo(o2.getTrainTag());
				}
			};
		}

	};

	public abstract Comparator<TrainBean> getComparator(final String order);

	public static TrainBeanComparator getByName(String name) {
		for (TrainBeanComparator c : values()) {
			if (c.name().equalsIgnoreCase(name)) {
				return c;
			}
		}
		return DEFAULT;
	}
}
