def sortByLast(v):
    for i in range(0, len(v) - 1):
        for j in range(i + 1, len(v)):
            if v[i][len(v[i]) - 1] < v[j][len(v[j]) - 1]:
                v[i], v[j] = v[j], v[i]


def rankByQuality(v, qualityFunc):
    qualities = []
    ranked = v.copy()
    for i in range(0, len(ranked) - 1):
        for j in range(i + 1, len(ranked)):
            if qualityFunc(ranked[i]) < qualityFunc(ranked[j]):
                ranked[i], ranked[j] = ranked[j], ranked[i]
    for it in ranked:
        qualities.append(qualityFunc(it))

    return ranked, qualities
