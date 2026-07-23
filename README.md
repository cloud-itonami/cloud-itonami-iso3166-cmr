# cloud-itonami-iso3166-cmr

**`:implemented`** for **CMR**. Flagship `cm-entity-missing`, tax `rccm-unverified`.

```
clojure -M:dev:test
```

AGPL-3.0-or-later.

## Market-entry / statute catalogs

Governed public-sector market-entry compliance actor, same architecture
as `cloud-itonami-iso3166-cog`/`-tcd`/`-tgo` (fellow OHADA member
states):

- `src/marketentry/{facts,governor,phase,sim,operation,registry,store,
  marketentryllm}.cljc` -- the actor. `facts.cljc` cites the Agence de
  Régulation des Marchés Publics (ARMP) / Code des marchés publics for
  procurement, and RCCM/NIU for business/tax registration (MINMIDT /
  DGI). Note: this iteration independently re-tested the file's own
  `armp.cm` provenance URL and found it currently does NOT resolve
  (DNS failure) -- an honest, disclosed observation for a future
  maintainer of this file, not fixed here (out of this statute-catalog
  task's scope).
- `src/statute/facts.cljc` -- general-law catalog, orthogonal to
  `marketentry.facts`: the OHADA Uniform Act on Commercial Companies
  (AUSCGIE, directly applicable, no domestic transposition act),
  Cameroon's own Code du Travail (Loi n°92-007 du 14 août 1992), the
  investment-incentives law (Loi n° 2013/004 du 18 avril 2013,
  independently confirmed via UNCTAD's Investment Policy Hub AND a
  corroborating droit-afrique.com copy), and the Direction Générale
  des Impôts' (DGI) own institutional-organization decrees (Décret n°
  2013/066 du 28 février 2013 + Décret n° 2005/119 du 15 avril 2005).
  A Code Général des Impôts (CGI) base enacting-law-number citation
  could not be independently confirmed this iteration -- an honest gap;
  see the namespace docstring.

Every citation is WebFetch/curl/pdftotext-verified against an official
or official-adjacent source (ohada.org, impots.cm,
investmentpolicy.unctad.org, droit-afrique.com). Where a primary site
returned a bot-detection-adjacent block (droit-afrique.com returned
HTTP 403 on a direct fetch), the Internet Archive Wayback Machine was
used as the documented fallback -- never a browser-automation bypass;
see each namespace's docstring for the full research trail and any
honestly-narrowed scope or disclosed gap.

## Culture catalog

Alongside the market-entry / statute catalogs, this repo carries a
**country-level regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) — national dishes, protected products, beverages,
crafts, festivals and heritage sites for Cameroon:

- `src/culture/facts.cljc` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.
