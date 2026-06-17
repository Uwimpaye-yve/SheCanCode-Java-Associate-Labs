### Exercise 1.2 - README Benchmark Findings

1. **Insertion Analysis:** `ArrayList` and `HashSet` showcase the fastest insertion speeds. `TreeSet` is noticeably slower because it executes an internal sorting algorithm every time a new element is introduced.
2. **Lookup Analysis:** `HashSet` wins by a landslide. It maps keys using hashes directly to memory addresses, resulting in near-instant lookup speeds. `LinkedList` is the slowest because it must sequentially scan through elements.

### Optimal Structure for 'Recently Viewed Products' Feature

**The Best Choice:** `LinkedHashSet` (or a combination of a `LinkedList` and a `HashSet`).

**Justification:**
A "Recently Viewed Products" feature requires two specific rules:
1. It must preserve insertion order (the item you clicked 5 seconds ago must sit at the top of the history list).
2. It must reject duplicates (viewing a phone twice shouldn't fill up the history page with duplicates of the same phone).

While standard `HashSet` discards ordering and `ArrayList` allows messy duplicate entries, **`LinkedHashSet`** provides the perfect middle ground by utilizing a hash table for fast lookups while maintaining a running link layout to remember item timelines.