const API = '/api/colaboradores';

// pega os elementos do HTML pelo id
const form = document.getElementById('formColaborador');
const campoId = document.getElementById('id');
const campoNome = document.getElementById('nome');
const campoCargo = document.getElementById('cargo');
const campoData = document.getElementById('dataAdmissao');
const campoDepto = document.getElementById('departamento');
const corpoTabela = document.getElementById('corpoTabela');
const mensagem = document.getElementById('mensagem');
const tituloForm = document.getElementById('tituloForm');
const busca = document.getElementById('busca');

// ---------- CARREGAR (GET) ----------

async function carregarDepartamentos() {
    const resposta = await fetch('/api/departamentos'); // chama o DepartamentoController
    const lista = await resposta.json();                // converte o JSON em lista

    lista.forEach(d => {
        const opcao = document.createElement('option');
        opcao.value = d.id;
        opcao.textContent = d.nome;
        campoDepto.appendChild(opcao);
    });
}

async function carregarColaboradores() {
    const filtro = busca.value.trim();
    const url = filtro ? `${API}?cargo=${encodeURIComponent(filtro)}` : API;

    const resposta = await fetch(url);
    const lista = await resposta.json();

    corpoTabela.innerHTML = ''; // limpa a tabela

    lista.forEach(c => {
        const linha = document.createElement('tr');
        linha.innerHTML = `
            <td>${c.nome}</td>
            <td>${c.cargo}</td>
            <td>${c.departamento ? c.departamento.nome : '-'}</td>
            <td>${formatarData(c.dataAdmissao)}</td>
            <td>
                <button class="editar">Editar</button>
                <button class="excluir">Excluir</button>
            </td>`;

        linha.querySelector('.editar').addEventListener('click', () => preencherFormulario(c));
        linha.querySelector('.excluir').addEventListener('click', () => excluir(c.id));

        corpoTabela.appendChild(linha);
    });
}

// ---------- SALVAR (POST ou PUT) ----------

form.addEventListener('submit', async (evento) => {
    evento.preventDefault(); // impede a pagina de recarregar

    const corpo = {
        nome: campoNome.value,
        cargo: campoCargo.value,
        dataAdmissao: campoData.value || null,
        departamento: campoDepto.value ? { id: Number(campoDepto.value) } : null
    };

    const editando = campoId.value !== '';
    const url = editando ? `${API}/${campoId.value}` : API;
    const metodo = editando ? 'PUT' : 'POST';

    const resposta = await fetch(url, {
        method: metodo,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(corpo) // objeto JS -> texto JSON
    });

    if (!resposta.ok) { // 400 ou 404: mostra o erro que o TratadorDeErros montou
        const erro = await resposta.json();
        mostrarMensagem(Object.values(erro).join(' | '), 'erro');
        return;
    }

    mostrarMensagem(editando ? 'Colaborador atualizado.' : 'Colaborador cadastrado.', 'sucesso');
    limparFormulario();
    carregarColaboradores();
});

// ---------- EXCLUIR (DELETE) ----------

async function excluir(id) {
    if (!confirm('Excluir este colaborador?')) return;

    await fetch(`${API}/${id}`, { method: 'DELETE' });
    mostrarMensagem('Colaborador excluido.', 'sucesso');
    carregarColaboradores();
}

// ---------- FORMULARIO ----------

function preencherFormulario(c) {
    campoId.value = c.id;
    campoNome.value = c.nome;
    campoCargo.value = c.cargo;
    campoData.value = c.dataAdmissao;
    campoDepto.value = c.departamento ? c.departamento.id : '';
    tituloForm.textContent = 'Editando colaborador';
}

function limparFormulario() {
    form.reset();
    campoId.value = '';
    tituloForm.textContent = 'Novo colaborador';
}

document.getElementById('btnCancelar').addEventListener('click', limparFormulario);

// ---------- UTILIDADES ----------

function formatarData(iso) {
    if (!iso) return '-';
    const [ano, mes, dia] = iso.split('-'); // "2024-03-15" -> ["2024","03","15"]
    return `${dia}/${mes}/${ano}`;
}

function mostrarMensagem(texto, tipo) {
    mensagem.textContent = texto;
    mensagem.className = tipo;
}

busca.addEventListener('input', carregarColaboradores); // filtra a cada letra digitada

// ---------- INICIO ----------
carregarDepartamentos();
carregarColaboradores();