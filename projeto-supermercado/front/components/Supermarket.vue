<template>
  <div class="p-4 max-w-xl mx-auto">
    <h1 class="text-2xl font-bold mb-4">Supermercado</h1>

    <div v-if="!compraFinalizada">
      <div class="mb-4">
        <label class="block mb-2">Digite seu saldo:</label>
        <input v-model.number="saldoInicial" type="number" class="border p-2 w-full" :disabled="saldoDefinido" />
        <button v-if="!saldoDefinido" @click="definirSaldo" class="bg-green-500 text-white px-4 py-2 mt-2">Definir saldo</button>
      </div>

      <div v-if="saldoDefinido">
        <p class="mb-4">Saldo restante: R$ {{ saldoRestante.toFixed(2) }}</p>

        <div class="grid grid-cols-2 gap-4 mb-4">
          <button
              v-for="fruta in frutas"
              :key="fruta.nome"
              class="bg-yellow-300 hover:bg-yellow-400 text-black font-semibold py-2 px-4 rounded"
              @click="adicionarFruta(fruta)"
              :disabled="fruta.preco > saldoRestante"
          >
            {{ fruta.nome }} - R$ {{ fruta.preco }}
          </button>
        </div>

        <h2 class="text-lg font-bold">Sacola:</h2>
        <ul class="mb-4">
          <li v-for="item in sacola" :key="item.nome + Math.random()">
            {{ item.nome }} - R$ {{ item.preco }}
          </li>
        </ul>

        <button @click="finalizarCompra" class="bg-blue-500 text-white px-4 py-2">Finalizar Compra</button>
      </div>
    </div>

    <div v-else>
      <p class="text-green-600 text-xl font-bold mb-4">
        Compra finalizada! Total gasto: R$ {{ totalGasto.toFixed(2) }}
      </p>
      <button @click="novaCompra" class="bg-gray-800 text-white px-4 py-2">Nova Compra</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const saldoInicial = ref(0)
const saldoRestante = ref(0)
const saldoDefinido = ref(false)
const sacola = ref([])
const compraFinalizada = ref(false)

const frutas = [
  { nome: 'Maçã', preco: 3 },
  { nome: 'Banana', preco: 2 },
  { nome: 'Uva', preco: 4 },
  { nome: 'Melancia', preco: 5 }
]

const definirSaldo = () => {
  saldoRestante.value = saldoInicial.value
  saldoDefinido.value = true
}

const adicionarFruta = (fruta) => {
  if (fruta.preco <= saldoRestante.value) {
    sacola.value.push(fruta)
    saldoRestante.value -= fruta.preco
  } else {
    alert('Saldo insuficiente!')
  }
}

const finalizarCompra = () => {
  compraFinalizada.value = true
}

const novaCompra = () => {
  saldoInicial.value = 0
  saldoRestante.value = 0
  saldoDefinido.value = false
  sacola.value = []
  compraFinalizada.value = false
}

const totalGasto = computed(() =>
    sacola.value.reduce((soma, fruta) => soma + fruta.preco, 0)
)
</script>

<style>
body {
  font-family: sans-serif;
}
</style>
