# cloud-itonami-iso3166-svn

Open ISO 3166 Blueprint for **SVN**: Republic of Slovenia (EU member
state, eurozone).

**`:implemented`** for **SVN**. Flagship `fdi-notification-missing`
(Zakon o spodbujanju investicij / ZSInv, amended provisions
implementing EU Regulation 2019/452).

```
kbb -M:dev:test
```

Independent public-sector market-entry & procurement-compliance service
for an already-incorporated operator entering public contracts in Slovenia.

## Official surface (web-verified)

- Procurement: e-JN (Elektronsko javno naročanje, ejn.gov.si) is the
  public e-procurement portal, operated by Ministrstvo za notranje
  zadeve in javno upravo (Ministry of the Interior and Public
  Administration); it exposes electronic tender submission, an
  electronic catalogue, and bidder ("ponudniki") registration. The
  Zakon o javnem naročanju (Public Procurement Act) is the legal
  basis (confirmed via gov.si citing "23. člen Zakona o javnem
  naročanju" -- the exact version suffix/gazette number was not
  independently confirmed this session, see `src/marketentry/facts.cljk`).
  Procurement-award legality is reviewed by the Državna revizijska
  komisija (DKOM, dkom.si) -- "Državna revizijska komisija je poseben,
  neodvisen in samostojen državni organ nadzora, ki odloča o
  zakonitosti v vseh stopnjah postopka javnega naročanja."
- Business/tax: Agencija Republike Slovenije za javnopravne evidence in
  storitve (AJPES, ajpes.si) runs the Poslovni register Slovenije
  (Business Register of Slovenia); Finančna uprava Republike Slovenije
  (FURS, fu.gov.si) assigns the davčna številka (tax number),
  including to foreign legal entities via Form DR-04 before they
  commence business activity in Slovenia.
- Foreign investment: Slovenia HAS adopted an FDI-screening mechanism
  implementing EU Regulation 2019/452, via amended provisions of the
  Zakon o spodbujanju investicij (ZSInv, Law on Investment Promotion).
  Notification to Ministrstvo za gospodarstvo, delo in šport (Ministry
  of Economy, Labour and Sport) is required when a foreign investor
  acquires >= 10% of the capital/voting rights of a Slovenian company
  AND that company's activity touches a listed risk factor (critical
  infrastructure, critical technologies, supply of critical resources,
  access to sensitive information, media pluralism, or EU-interest
  projects/programmes) -- this asymmetry with Serbia (EU-candidate,
  no FDI-screening regime) and with Poland's blueprint (whose flagship
  is an EU-establishment/NIP pair, not FDI-screening) is deliberate and
  disclosed, not an omission.

See `src/marketentry/facts.cljk` and `src/statute/facts.cljk` for the
full citation trail and an explicit provenance disclosure (several
exact Uradni list RS gazette numbers were not rendered by any official
page fetched this session -- disclosed as gaps, not fabricated).

## What this is NOT

- **Not the government of Slovenia.**

## License

AGPL-3.0-or-later.

## Culture catalog

Alongside the market-entry / statute catalogs, this repo carries a
**country-level regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) — national dishes, protected products, beverages,
crafts, festivals and heritage sites for Slovenia:

- `src/culture/facts.cljk` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.
