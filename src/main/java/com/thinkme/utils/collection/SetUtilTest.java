package com.thinkme.utils.collection;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;
import com.thinkme.utils.collection.type.ConcurrentHashSet;

import com.google.common.collect.Ordering;

public class SetUtilTest {

	@Test
	public void guavaBuildSet() {
		HashSet<String> set1 = com.thinkme.utils.collection.SetUtil.newHashSet();

		HashSet<String> set2 = com.thinkme.utils.collection.SetUtil.newHashSetWithCapacity(10);

		HashSet<String> set3 = com.thinkme.utils.collection.SetUtil.newHashSet("1", "2", "2");

		assertThat(set3).hasSize(2).contains("1", "2");

		HashSet<String> set4 = com.thinkme.utils.collection.SetUtil.newHashSet(ListUtil.newArrayList("1", "2", "2"));
		assertThat(set4).hasSize(2).contains("1", "2");

		TreeSet<String> set5 = com.thinkme.utils.collection.SetUtil.newSortedSet();

		TreeSet<String> set6 = com.thinkme.utils.collection.SetUtil.newSortedSet(Ordering.natural());

		ConcurrentHashSet set7 = com.thinkme.utils.collection.SetUtil.newConcurrentHashSet();
	}

	@Test
	public void jdkBuildSet() {
		Set<String> set1 = com.thinkme.utils.collection.SetUtil.emptySet();
		assertThat(set1).hasSize(0);

		Set<String> set2 = com.thinkme.utils.collection.SetUtil.emptySetIfNull(null);
		assertThat(set2).isNotNull().hasSize(0);

		Set<String> set3 = com.thinkme.utils.collection.SetUtil.emptySetIfNull(set1);
		assertThat(set3).isSameAs(set1);

		try {
			set1.add("a");
			fail("should fail before");
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(UnsupportedOperationException.class);
		}

		Set<String> set4 = com.thinkme.utils.collection.SetUtil.singletonSet("1");
		assertThat(set4).hasSize(1).contains("1");
		try {
			set4.add("a");
			fail("should fail before");
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(UnsupportedOperationException.class);
		}

		Set<String> set5 = com.thinkme.utils.collection.SetUtil.newHashSet();
		Set<String> set6 = com.thinkme.utils.collection.SetUtil.unmodifiableSet(set5);

		try {
			set6.add("a");
			fail("should fail before");
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(UnsupportedOperationException.class);
		}

		Set<String> set7 = com.thinkme.utils.collection.SetUtil.newSetFromMap(MapUtil.<String, Boolean> newConcurrentSortedMap());
	}

	@Test
	public void collectionCaculate() {
		HashSet<String> set1 = com.thinkme.utils.collection.SetUtil.newHashSet("1", "2", "3", "6");
		HashSet<String> set2 = com.thinkme.utils.collection.SetUtil.newHashSet("4", "5", "6", "7");

		Set<String> set3 = com.thinkme.utils.collection.SetUtil.unionView(set1, set2);
		assertThat(set3).hasSize(7).contains("1", "2", "3", "4", "5", "6", "7");

		Set<String> set4 = com.thinkme.utils.collection.SetUtil.intersectionView(set1, set2);
		assertThat(set4).hasSize(1).contains("6");

		Set<String> set5 = com.thinkme.utils.collection.SetUtil.differenceView(set1, set2);
		assertThat(set5).hasSize(3).contains("1", "2", "3");

		Set<String> set6 = com.thinkme.utils.collection.SetUtil.disjointView(set1, set2);
		assertThat(set6).hasSize(6).contains("1", "2", "3", "4", "5", "7");

		try {
			set6.add("a");
			fail("should fail before");
		} catch (Throwable t) {
			assertThat(t).isInstanceOf(UnsupportedOperationException.class);
		}
	}

}
