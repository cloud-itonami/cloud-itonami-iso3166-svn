# Business Model: Independent Public-Sector Market-Entry & Procurement Compliance Service — Republic of Slovenia

## Classification

- Repository: `cloud-itonami-iso3166-svn`
- ISO 3166: `SVN` (Republic of Slovenia)
- Activity: public-procurement market-entry and ongoing regulatory-
  compliance navigation for an already-incorporated operator
- Social impact: [:sme-market-access :public-spend-transparency :cross-border-friction-reduction]

## Customer

- an already-incorporated `cloud-itonami-cofog-{code}` /
  `cloud-itonami-isco-{code}` / `cloud-itonami-unspsc-{segment}` /
  `cloud-itonami-{ISIC}` operator wanting to bid on a Slovenian public
  contract
- a foreign SME or civic-tech vendor entering the public sector in
  Slovenia for the first time
- a `cloud-itonami-M6910` client that has just completed incorporation
  and now needs public-sector market access

## Offer

- registration walkthrough for the e-JN (Elektronsko javno naročanje,
  ejn.gov.si) portal, operated by Ministrstvo za notranje zadeve in
  javno upravo (Ministry of the Interior and Public Administration),
  under the Zakon o javnem naročanju (Public Procurement Act)
- business/tax registration checklist: an AJPES (Agencija Republike
  Slovenije za javnopravne evidence in storitve) Poslovni register
  Slovenije entry plus a davčna številka (tax number) assigned by
  Finančna uprava Republike Slovenije (FURS) -- including, for a
  foreign legal entity with no seat/registration in Slovenia, the
  Form-DR-04 tax-register application required BEFORE commencing
  business activity in Slovenia
- foreign-direct-investment screening navigation: Slovenia, as an EU
  member state, has adopted a national FDI-screening mechanism
  implementing Regulation (EU) 2019/452, via amended provisions of the
  Zakon o spodbujanju investicij (ZSInv). Where the operator's own
  Slovenian market presence was established by a foreign investor
  acquiring >= 10% of the capital/voting rights of an existing
  Slovenian company, AND that company's activity touches a listed risk
  factor (a realistic overlap for public-sector-adjacent activity:
  critical infrastructure, critical technologies, supply of critical
  resources, access to sensitive information, media pluralism, or
  EU-interest projects/programmes), this service tracks whether the
  required notification to Ministrstvo za gospodarstvo, delo in šport
  (Ministry of Economy, Labour and Sport) was filed
- ongoing regulatory-change monitoring subscription
- compliance-audit export package for the client's own records

## Revenue

- per-engagement market-entry fee (one-time registration + checklist
  completion)
- recurring regulatory-change monitoring subscription
- compliance-audit export package

## Trust Controls

- any actual e-JN registration or filing submission requires
  Market-Entry Compliance Governor clearance and always escalates to
  human sign-off (`:filing/submit` is never automated at any phase)
- a false or fabricated regulatory-requirement claim is a HARD hold
  that cannot be overridden by human approval alone — it must be
  corrected against a cited official source first
- this service does **not** provide legal or tax advice; characterization
  and filing on the client's behalf beyond checklist/draft assistance
  routes to Slovenian-licensed counsel or a registered agent
- every requirement cites the official portal or regulation, never
  invented; where an exact citation detail (e.g. an Uradni list RS
  gazette number) could not be independently confirmed this session,
  the gap is disclosed explicitly in `src/marketentry/facts.cljc` and
  `src/statute/facts.cljc` rather than papered over

## Honest disclosure: Slovenia's FDI-screening mechanism IS real, unlike Serbia's

Unlike Serbia (an EU-candidate, non-member sibling in this fleet, whose
Zakon o ulaganjima gives foreign investors national treatment with no
screening threshold), Slovenia's EU membership brings a REAL national
implementation of Regulation (EU) 2019/452's FDI-screening framework —
the Zakon o spodbujanju investicij (ZSInv), amended provisions,
notification via Ministrstvo za gospodarstvo, delo in šport. This is
stated here as a positive, independently verified structural fact about
Slovenia's actual regime, not a gap in this catalog's coverage, and not
copied from Poland's differently-shaped EU-establishment/NIP flagship —
see `src/marketentry/governor.cljc` for the full reasoning.

## Boundary with adjacent actors (read before forking)

- **`com-etzhayyim-ooyake`** (etzhayyim/root): read-only civic-wayfinding
  mirror of government structure, non-commercial, barred from acting as
  or for the government (G3 impersonation ban). This blueprint is
  commercial and never claims to be an official channel.
- **`matsurigoto`** (etzhayyim/root): sovereign e-government statecraft —
  literally the government, for etzhayyim's own covenant or an adopting
  nation-state. This blueprint is an independent operator the government
  contracts with or that bids into its procurement — never the
  government.
- **`com-etzhayyim-toritsugi`** (etzhayyim/root): guides a consenting
  INDIVIDUAL citizen through their OWN procedure, non-profit,
  donation-only. This blueprint's client is a business operator, not an
  individual citizen, and it is commercial.
- **`legal-entity.etzhayyim.com`**: read-only aggregated company-registry
  data, no execution. This blueprint executes (gated) registrations.
- **`cloud-itonami-M6910`**: helps a client BECOME a legal entity
  (incorporation, ISIC 6910) — a prior, different regulatory phase
  (company law, Zakon o gospodarskih družbah). This blueprint assumes
  incorporation is already done and handles public-procurement market
  entry (a different regulatory domain).
- **`cloud-itonami-cofog-{code}`**: a jurisdiction-agnostic operator
  template for ONE public function. This blueprint is the orthogonal
  jurisdiction-specific axis — the two compose (fork a COFOG-function
  blueprint AND this one to operate in Slovenia).
