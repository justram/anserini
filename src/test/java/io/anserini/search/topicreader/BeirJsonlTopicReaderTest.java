/*
 * Anserini: A Lucene toolkit for reproducible information retrieval research
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.anserini.search.topicreader;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.SortedMap;

import static org.junit.Assert.assertEquals;

public class BeirJsonlTopicReaderTest {

  @Test
  public void test() throws IOException {
    TopicReader<String> reader = new BeirJsonlTopicReader(
        Paths.get("src/main/resources/topics-and-qrels/topics.beir-v1.0.0-robust04.test.jsonl.gz"));

    SortedMap<String, Map<String, String>> topics = reader.read();

    assertEquals(249, topics.keySet().size());
    assertEquals("301", topics.firstKey());
    assertEquals("Identify organizations that participate in international criminal activity, the activity, and, if possible, collaborating organizations and the countries involved.",
        topics.get(topics.firstKey()).get("title"));
    assertEquals("International Organized Crime",
        topics.get(topics.firstKey()).get("metadata_title"));
    assertEquals("A relevant document must as a minimum identify the organization and the type of illegal activity (e.g., Columbian cartel exporting cocaine). Vague references to international drug trade without identification of the organization(s) involved would not be relevant.",
        topics.get(topics.firstKey()).get("metadata_narrative"));
    assertEquals("700", topics.lastKey());
    assertEquals("What are the arguments for and against an increase in gasoline taxes in the U.S.?",
        topics.get(topics.lastKey()).get("title"));
    assertEquals("gasoline tax U.S.",
        topics.get(topics.lastKey()).get("metadata_title"));
    assertEquals("Relevant documents present reasons for or against raising gasoline taxes in the U.S.  Documents discussing rises or decreases in the price of gasoline are not relevant.",
        topics.get(topics.lastKey()).get("metadata_narrative"));
  }
}