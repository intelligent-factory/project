<template>

  <div>
    <router-view/>
  </div>



</template>
<script>
import AsideTab from "@/components/asideTab";
import HeadBar from "@/components/headBar";
export default {
  name: "App",
  components: {
    AsideTab,
    HeadBar
  },
  data() {
    return {}
  },

}
</script>
<style>

</style>
