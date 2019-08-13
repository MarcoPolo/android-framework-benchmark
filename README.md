# A terrible benchmark to compare various Android UI Frameworks

Hi! Welcome to the repo. Let me first say that benchmarks are very hard! How
can you automatically derive a number that relates to a user's experience? How
can you avoid people gaming the benchmarks? When is good enough enough?

Let me also say that this is by no means a fair, applicable, or even good benchmark set.

Check out my [list of criticism](#criticisms) (And feel free to [add your own!](https://github.com/MarcoPolo/android-framework-benchmark/edit/master/README.md))

## Methodology

This benchmark follows the same test as this [JS one](https://github.com/krausest/js-framework-benchmark). If you haven't taken a look at that one, please do! I'll quickly and poorly summarize it here.

The benchmark hopes to compare how frameworks handle:

1. Additions
1. Subtractions
1. Updates
1. Swaps

A special emphasis (imo) should be given to Updates. Most of the time the
user is only interacting with a subset of what's happening on the screen.
When they press a button or input text they expect quick feedback. Compare
this with the case where a user is loading a screen with 1000 Views. This
will (hopefully) occur infrequently compared to the times the user is
expecting to see a relatively small number of views (<= 100) of views
updating.

Another facet which is especially important on mobile devices is power and
memory consumption. A framework is better in this case if it uses minimal
memory, cpu, and IO.

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
