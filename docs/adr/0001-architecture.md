# ADR-0001: SVN marketentry `:implemented`

Flagship `fdi-notification-missing` -- Zakon o spodbujanju investicij
(ZSInv), amended provisions implementing Regulation (EU) 2019/452 on
screening foreign direct investment. Notification to Ministrstvo za
gospodarstvo, delo in šport (Ministry of Economy, Labour and Sport) is
required when a foreign investor acquires >= 10% of the capital or
voting rights of a Slovenian company AND that company's activity
touches a listed risk factor (critical infrastructure, critical
technologies, supply of critical resources, access to sensitive
information, media pluralism, or EU-interest projects/programmes) --
independently WebFetch-verified against https://www.gov.si/en/topics/foreign-direct-investments/
and https://www.gov.si/teme/spodbujanje-investicij/ this tick.

This is deliberately NOT a copy of either sibling's flagship shape:

- Poland (EU member): flagship pair is `eu-establishment-missing` +
  `nip-unverified`, grounded in EU freedom-of-establishment practice
  and Poland's own NIP/KRS numbers -- no FDI-screening check in that
  blueprint.
- Serbia (EU-candidate, non-member): flagship is `nonresident-pib-missing`,
  grounded in Art. 26 Zakon o poreskom postupku i poreskoj
  administraciji; Serbia's own README explicitly discloses NO
  FDI-screening regime (Zakon o ulaganjima gives foreign investors
  national treatment with no notification threshold) -- a genuine
  asymmetry with Slovenia, not an omission on either blueprint's part.

Slovenia's EU membership is EXACTLY why it has an FDI-screening
mechanism (Regulation (EU) 2019/452 is an EU instrument) -- the
structurally distinguishing fact independently verified this session.
Adding a fabricated eu-establishment/NIP-style pair to this blueprint,
copying Poland's shape without an independently verified Slovenia-
specific hook, would have been dishonest; a smaller, single, genuinely
grounded check is preferred. See `src/marketentry/governor.cljc`
docstring for the full reasoning and citation trail, and
`src/marketentry/facts.cljc` / `src/statute/facts.cljc` for the
complete disclosure of what was and was not independently confirmed
this session (several exact Uradni list RS gazette numbers could not
be rendered from any page fetched and are disclosed as gaps rather
than invented).
