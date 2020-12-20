<template>
  <div class="tabs">
    <el-tag
        :key="tag.id"
        v-for="tag in tags"
        :closable="tag.url != '/'"
        :disable-transitions="false"
        @click="changeMenu(tag)"
        @close="handleClose(tag)">
      {{tag.name}}
    </el-tag>
  </div>
</template>

<script>
import {mapMutations, mapState} from "vuex";

export default {
  name: "CommonTab",
  data() {
    return {}
  },
  computed: {
    ...mapState({
      tags: state => state.tab.tabsList
    })
  },
  methods: {
    ...mapMutations({
      close: 'closeTab'
    }),
    handleClose(tag) {
      this.close(tag)
    },
    changeMenu(item){
      this.$router.push({name: item.name})
      this.$store.commit("selectMenu",item);
    },

    showInput() {
      this.inputVisible = true;
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },

    handleInputConfirm() {
      let inputValue = this.inputValue;
      if (inputValue) {
        this.dynamicTags.push(inputValue);
      }
      this.inputVisible = false;
      this.inputValue = '';
    }
  }
}
</script>

<style lang="scss" scoped>
.tabs{
  .el-tag{
    margin-right: 20px;
  }
}
</style>
