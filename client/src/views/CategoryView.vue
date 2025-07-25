<template>
  <main class="container">
    <h1>Quản lý category</h1>
    <CategoryFormComponent />
    <table class="table">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Category Code</th>
          <th scope="col">Category Name</th>
          <th scope="col">Action</th>
        </tr>
      </thead>
      <tbody>
        <template v-for="(item, index) in categories" :key="item.id">
          <tr>
            <td>{{ index }}</td>
            <td>{{ item.categoryCode }}</td>
            <td>{{ item.categoryCode }}</td>
            <td>
              <button type="button" class="btn btn-danger" style="margin-right: 10px">
                Detail
              </button>
              <button type="button" class="btn btn-warning">Delete</button>
            </td>
          </tr>
        </template>
      </tbody>
    </table>
  </main>
</template>

<script setup>
import CategoryFormComponent from '@/components/CategoryFormComponent.vue'
import { getCategories } from '@/services/CategoryService'
import { onMounted, ref } from 'vue'
const categories = ref([])
// async function a(){

// }
// arrow function
const getListCate = async () => {
  try {
    const res = await getCategories()
    categories.value = res.data
  } catch (error) {
    console.log(error)
  }
}
onMounted(getListCate)
</script>

<style scoped>
h1 {
  text-align: center;
}
</style>
