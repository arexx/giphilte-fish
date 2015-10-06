# GiphilteFish

A giphy client written for a coding challenge. But also maybe fun to use.

## Installation

- Clone repo
- Open in Android Studio
- Press "run"

## Known Issues/Weaknesses

- When you select a gif, the DetailFragment overlays the SearchFragment, but the SearchFragment is not cleaned up (and the animations keep running too). This burns CPU and memory.
- There's very little error handling. If there's any kind of network or JSON issue you'll just get a complainy dialog. When images don't load, you just see blank tiles.
- Images are not preloaded in advance of becoming visible on the screen, so the app is a lot more fun to use on a very fast internet connection.
- Only one page of results (up to 25) are ever loaded for any given query. No infinite scroll here. (Yet?)
