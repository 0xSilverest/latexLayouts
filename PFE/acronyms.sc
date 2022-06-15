import java.nio.file.{Paths, Files}
import java.nio.charset.StandardCharsets

val source = scala.io.Source.fromFile("src/acronyms.tex").mkString

Files.write(
  Paths.get("src/acronyms.tex"),
  ("""\begin{table}[!htbp]
    \centering
    \begin{tabular}{| c | c |}
        \hline
        """ +
  source.split("\n").toList
    .map(_.trim)
    .filter(l => !l.contains("\\hline")
      && !l.startsWith("\\")
      && !l.isEmpty
    )
    .map(_.replace("\\\\", ""))
    .sorted
    .mkString("\\\\\n        \\hline\n        ")
    + """\\
        \hline
    \end{tabular}
\end{table}
    """)
    .getBytes(StandardCharsets.UTF_8))

