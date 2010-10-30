make-void -- Generating voiD statistics for RDF files
-----------------------------------------------------

OVERVIEW

  This script computes statistics about RDF files, such as the total number
  of triples, classes and properties; the number of instances for each
  class; the number of uses of each property; and the number of triples that
  link a subject on one domain to an object on another domain.

  The output is an RDF file that uses the voiD vocabulary [1] to capture the
  statistics in a machine-readable form. It doesn't use the official version
  of the voiD vocabulary, but an unreleased editor's draft of the upcoming
  next version of voiD [2].

  The script computes statistics by running aggregate queries over the input
  files, using the ARQ query engine [3].

  [1] http://vocab.deri.ie/void/guide
  [2] http://void-impl.googlecode.com/svn/trunk/guide2/index.html
  [3] http://jena.sourceforge.net/ARQ/


REQUIREMENTS

  * Unix shell environment
  * ARQ version 2.8.6 or later -- doesn't work on 2.8.5 or before


LIMITATIONS

  * Size of processable RDF files is limited by availabe memory


INSTALLATION

  1. Make sure that ARQ is installed and configured. The 'arq' command
     must be available on the path.

  2. Copy hostname-function.jar into ARQ's /lib directory.


USAGE

  ./make-void [options] file [...]

  The input files must be in RDF/XML or Turtle format. They can be local
  files or HTTP URIs.

  Options:

  --dataset-uri <uri>
      Use this URI as the identifier of the void:Dataset in the generated
      voiD file. Defaults to a URI relative to the output file name.

  --uri-space <uri>
      All URIs starting with this URI prefix are considered entities in the
      dataset, while other URIs are considered as members of other, linked
      datasets. If not specified, all URIs will be counted as entities.

  --min <num>
      Linksets, class partitions, and property partitions with less than
      <num> members will be ignored. Setting this can make the generated
      voiD description more useful. By default, all sets and partitions are
      included.

  The output will be saved as <void-output.ttl> in the current directory.


HOW IT WORKS

  * ARQ queries (not pure SPARQL)
  * Several CONSTRUCT queries that together form the result graph
  * Merging of the result graphs is done simply by concatenating (and
    filtering the @prefix statements from all but the first output, just
    to make the final file look neater)
  * Parameters are passed to the query via a file <control.ttl> which is
    loaded as a named graph
  * Adds an extension function to ARQ for extracting the hostname part of
    a URI; we use this to separate the linksets
  * The build-jar script builds the extension function from source


COMMENTS AND CONTACT

  Richard Cyganiak <richard@cyganiak.de>
