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

## Motivation

I couldn't find a satisfying comparison between various frameworks. Even hot ones like React Native and Flutter only have [vague performance comparisons](https://www.google.com/search?q=react+native+vs+flutter+performance). I know each framework has it's own benchmarks as well, but I wanted something a bit more neutral.
I think this comparison is important because it can give perspective to both
designers and users of the UI frameworks. If someone comes up with a new UI
framework that claims to be 10x faster than X, it's really worth backing up
those claims on a neutral benchmark. If a user wants to pick framework X over
Y, it's important they be informed about the trade off they're making. For
example, if I want to make an app cross platform I may choose React Native
over Native android code because I can make an app for both iOS and Android
with the same codebase (and maybe even Desktop) On the other hand, if the
project involves a lot of user data updates and we expect snappy user
experience it may be worth investing the extra time in making a native
android app.

## Cross Platform

I hope to extend this with a special focus on cross platform frameworks.
Maybe even award more points for frameworks that are consistently good across
devices rather than good on just one platform. That said, it's important to keep in mind that when we write cross platform code we should be measuring ourselves against the best native solution (e.g. Native Android UI code, Litho) instead of other cross platform solutions (e.g. A WebView wrapper around a website).

## TODO

- Instrument Benchmarking Code
- Add Flutter
- Add Litho

## Criticisms

- This is not using Virtual List/Recycler View. \* This is on purpose. Otherwise it would be too easy
- It's not consistently styled
  _ I wanted to give these frameworks a benefit of the doubt.
  _ Styling can add a bit more overhead, so I figure I'd keep it simple
- You haven't optimized by doing X on Y
  _ If it's an easy and obvious optimization, my apologies. Send a PR!
  _ If it's obscure, I think it's better to not include it.
