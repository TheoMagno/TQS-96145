<h1> 1.f </h1>

<p> My project has passed the analysis, although it has 1 Bug, 1 Security Hotspot and 25 Code Smells. </p>

![img.png](img.png)

| Issue | Problem description                                                                                                                                              | How to solve                                                                                                                                                  |
| ---- |------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------|
 | Bug | Save and re-use this "Random" at Dip.java line 53                                                                                                                | Avoid reusing the random generator.                                                                                                                           |
 | Vulnerability | Make sure that using this pseudorandom number generator is safe here.                                                                                            | Use a pseudorandom number generator.                                                                                                                          |
 | Code smell (major) | Loop counter assigned inside the loop body. <br> Unused method parameter. <br> Use of prints instead of loggers. <br> Use of assertTrue instead of assertEquals. | * Refactor the code in order to not assign to this loop counter from within the loop body. <br> *Remove this unused method parameter "subset". <br> *Replace this use of System.out or System.err by a logger. <br> *Use assertEquals instead. |

<h1> 3.a </h1>

<p> As we have not used tests for the IES project it does not make sense using coverage as a quality gate, so I have chosen to use next gates:</p>

![img_1.png](img_1.png)

<p> These Quality Gates were chosen because Code Smells are something to avoid aiming to develop a legible and efficient code; besides that, duplicated lines to know if more methods could have been made, Maintainability Rating to have an easily maintainable code, Reliability to check if the code has lots of bugs, Security Hotspots and Security Rating to check for vulnerabilities and Technical Debt to avoid long refactoring.</p>

<p> With these QG I have reached the following results: </p>

![img_3.png](img_3.png)

<h1> 3.b </h1>

<p> For this exercise I basically tried only the Dispensables code smells. I have done unnecessary commentaries, duplicated codes and dead codes. After that I have reached the following results: </p>

![img_4.png](img_4.png)