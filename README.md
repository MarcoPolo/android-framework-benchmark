# A terrible benchmark to compare various Android UI Frameworks

Hi! Welcome to the repo. Let me first say that benchmarks are very hard! How
can you automatically derive a number that relates to a user's experience? How
can you avoid people gaming the benchmarks? When is good enough enough?

Let me also say that this is by no means a fair, applicable, or even good benchmark set.

Check out my [list of criticism](#criticisms) (And feel free to [add your own!](https://github.com/MarcoPolo/android-framework-benchmark/edit/master/README.md))

## TODO

- Instrument Benchmarking Code
- Add Litho

## Criticisms

- This is not using Virtual List/Recycler View. \* This is on purpose. Otherwise it would be too easy
- It's not consistently styled
  _ I wanted to give these frameworks a benefit of the doubt.
  _ Styling can add a bit more overhead, so I figure I'd keep it simple
- You haven't optimized by doing X on Y
  _ If it's an easy and obvious optimization, my apologies. Send a PR!
  _ If it's obscure, I think it's better to not include it.
