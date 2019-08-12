<script>
  let rowId = 1,
    data = buildData(300),
    selected = undefined;

  const add = () => (data = data.concat(buildData(1000))),
    clear = () => {
      data = [];
      selected = undefined;
    },
    partialUpdate = () => {
      for (let i = 0; i < data.length; i += 10) {
        data[i].label += " !!!";
      }
    },
    remove = num => {
      const idx = data.findIndex(d => d.id === num);
      data = [...data.slice(0, idx), ...data.slice(idx + 1)];
    },
    run = () => {
      data = buildData(1000);
      selected = undefined;
    },
    runLots = () => {
      data = buildData(10000);
      selected = undefined;
    },
    select = id => (selected = id),
    swapRows = () => {
      if (data.length > 998) {
        data = [data[0], data[998], ...data.slice(2, 998), data[1], data[999]];
      }
    };

  function _random(max) {
    return Math.round(Math.random() * 1000) % max;
  }

  function buildData(count = 1000) {
    const adjectives = [
        "pretty",
        "large",
        "big",
        "small",
        "tall",
        "short",
        "long",
        "handsome",
        "plain",
        "quaint",
        "clean",
        "elegant",
        "easy",
        "angry",
        "crazy",
        "helpful",
        "mushy",
        "odd",
        "unsightly",
        "adorable",
        "important",
        "inexpensive",
        "cheap",
        "expensive",
        "fancy"
      ],
      colours = [
        "red",
        "yellow",
        "blue",
        "green",
        "pink",
        "brown",
        "purple",
        "brown",
        "white",
        "black",
        "orange"
      ],
      nouns = [
        "table",
        "chair",
        "house",
        "bbq",
        "desk",
        "car",
        "pony",
        "cookie",
        "sandwich",
        "burger",
        "pizza",
        "mouse",
        "keyboard"
      ],
      data = [];
    for (var i = 0; i < count; i++)
      data.push({
        id: rowId++,
        label:
          adjectives[_random(adjectives.length)] +
          " " +
          colours[_random(colours.length)] +
          " " +
          nouns[_random(nouns.length)]
      });
    return data;
  }
</script>

<page>
  <flexboxlayout backgroundColor="#777777" flexDirection="column">
    <stackLayout flexShrink={0}>
      <label text="Svelte Native (keyed)" />
      <button text="Create 1k rows" on:tap={run} />
      <button text="Create 10k rows" on:tap={runLots} />
      <button text="Append 1k rows" on:tap={add} />
      <button text="Update every 10th row" on:tap={partialUpdate} />
      <button text="Clear" on:tap={clear} />
      <button text="Swap Rows" on:tap={swapRows} />
    </stackLayout>

    <scrollView flexGrow={1} backgroundColor="#EEE">
      <stackLayout>
        {#each data as row, num (row.id)}
          <stackLayout orientation="horizontal">
            <label text={row.id} />
            <label text={row.label} on:tap={() => select(row.id)} />
            <label marginLeft={8} text="X" on:tap={() => remove(row.id)} />
          </stackLayout>
        {/each}
      </stackLayout>
    </scrollView>
  </flexboxlayout>
</page>
