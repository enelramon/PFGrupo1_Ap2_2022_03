using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Ticket.Api.DAL;
using Ticket.Api.Models;
using Ticket.Api.Models.DTO;

namespace Ticket.Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ClientesController : ControllerBase
    {
        private readonly TicketDbContext _context;

        public ClientesController(TicketDbContext context)
        {
            _context = context;
        }

        #region INTERNOS 

        /* OBTENER LOS 5 CLIENTES QUE MAS ME RESPONDEN */
        [HttpGet("Top5Respondidos/{id}")]
        public async Task<ActionResult<IEnumerable<Clientes>>> GetClientesRespondidos(int id)
        {
            var lista = (
                from c in _context.Clientes
                join r in _context.Respuestas on c.ClienteId equals r.ClienteId
                from t in _context.Tickets
                where t.ClienteId == id && r.TicketId == t.TicketId
                orderby (c.Respuestas.Where(r2 => r2.TicketId == t.TicketId).Count())
                select c
                         )
                         .Take(5)
                         .Distinct()
                         .Include(c => c.Configuracion)
                         .Include(c => c.Tickets)
                         .Include(c => c.Respuestas)
                         .ToListAsync();
            return await lista;

        }

        /* OBTENER LOS 5 CLIENTES QUE MAS LE RESPONDO */
        [HttpGet("TopRespondidosPor_Mi/{id}")]
        public async Task<ActionResult<IEnumerable<Clientes>>> GetClientesRespondidosPor_Mi(int id)
        {
            var lista = (
                from c in _context.Clientes
                join r in _context.Respuestas on id equals r.ClienteId
                from t in _context.Tickets
                where t.ClienteId == c.ClienteId && r.TicketId == t.TicketId
                orderby (c.Respuestas.Where(r2 => r2.TicketId == t.TicketId).Count())
                select c)
                         .Take(5)
                         .Distinct()
                         .Include(c => c.Configuracion)
                         .Include(c => c.Tickets)
                         .Include(c => c.Respuestas)
                         .ToListAsync();

            return await lista;

        }

        /* OBTENER CLIENTE POR ID */
        [HttpGet("{id}")]
        public async Task<ActionResult<Clientes>> GetClientes(int id)
        {
            var clientes = await _context.Clientes
                .Where(c => c.ClienteId == id)
                .Include(c => c.Tickets)
                .Include(c => c.Respuestas)
                .Include(c => c.Configuracion)
                .FirstOrDefaultAsync();

            if (clientes == null)
            {
                return NotFound();
            }

            return clientes;
        }

        /* OBTENER CLIENTE POR USER Y CLAVE */
        [HttpGet("{nombre},{clave}")]
        public async Task<ActionResult<Clientes>> GetCliente(string nombre, string clave)
        {
            string decryptedNombre = "";

            foreach (var letra in nombre)
                decryptedNombre += CharacterDecrypter(letra);

            var clientes = await _context.Clientes.FirstOrDefaultAsync(c => c.Nombres.Equals(decryptedNombre) && c.Clave.Equals(clave));

            if (clientes == null)
            {
                return NotFound();
            }

            return clientes;
        }

        /* UPDATE CLIENTE */
        [HttpPut("{id}")]
        public async Task<IActionResult> PutClientes(int id, Clientes clientes)
        {
            if (id != clientes.ClienteId)
            {
                return BadRequest();
            }

            _context.Entry(clientes).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ClientesExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        /* CHANGE PASSWORD */
        [HttpPut("{id},{password}")]
        public async Task<IActionResult> ChangePassword(int id, string password)
        {
            var cliente = await _context.Clientes.FindAsync(id);

            if (cliente == null)
                return NotFound();

            cliente.Clave = password;
            _context.Entry(cliente).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ClientesExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }


        /* CREATE CLIENTE */
        [HttpPost]
        public async Task<ActionResult<Clientes>> PostClientes(CreateClienteDTO clientes)
        {
            var config = await _context.Configuraciones.FindAsync(clientes.ConfiguracionId);
            if (config == null)
                return NotFound();



            var newCliente = new Clientes
            {
                Nombres = clientes.Nombres,
                Clave = clientes.Clave,
                ConfiguracionId = clientes.ConfiguracionId,
                Configuracion = config
            };
            _context.Clientes.Add(newCliente);

            await _context.SaveChangesAsync();

            return CreatedAtAction("GetClientes", new { id = newCliente.ClienteId }, clientes);
        }
        #endregion

        #region FORANEOS

        /* CAMBIAR CONFIGURACION */
        [HttpPut("ChangeConfig")]
        public async Task<IActionResult> ChangeConfig(ChangeConfigDTO config)
        {
            var configuraciones = await _context.Configuraciones.FirstOrDefaultAsync(c => c.Theme == config.ThemeIndex && c.ColorSchemeIndex == config.ColorScheme);
            if (configuraciones == null)
                return NotFound();

            var clientes = await _context.Clientes.FindAsync(config.ClienteId);
            if (clientes == null)
                return BadRequest();

            clientes.Configuracion = configuraciones;
            clientes.ConfiguracionId = configuraciones.ConfiguracionId;
            _context.Entry(clientes).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ClientesExists(config.ClienteId))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        /* AGREGAR TICKET */
        [HttpPost("AddTicket")]
        public async Task<ActionResult<Clientes>> AddTicket(AddTicketDTO ticketDto)
        {
            var cliente = await _context.Clientes
                .Where(c => c.ClienteId == ticketDto.ClienteId)
                .Include(c => c.Configuracion)
                .FirstOrDefaultAsync();

            if (cliente == null)
                return NotFound();

            var sistema = await _context.Sistemas.FindAsync(ticketDto.SistemaId);
            if (sistema == null)
                return NotFound();

            var tipo = await _context.Tipos.FindAsync(ticketDto.TipoId);
            if (tipo == null)
                return NotFound();

            var prioridad = await _context.Prioridades.FindAsync(ticketDto.PrioridadId);
            if (prioridad == null)
                return NotFound();

            var estatus = await _context.Estatus.FindAsync(1);
            if (estatus == null)
                return NotFound();

            var newTicket = new Tickets
            {
                ClienteId = ticketDto.ClienteId,
                Cliente = cliente,
                SistemaId = ticketDto.SistemaId,
                Sistema = sistema,
                TipoId = ticketDto.TipoId,
                Tipo = tipo,
                PrioridadId = ticketDto.PrioridadId,
                Prioridad = prioridad,
                EstatusId = 1,
                Estatus = estatus,
                Especificaciones = ticketDto.Especificaciones
            };
            _context.Add(newTicket);

            await _context.SaveChangesAsync();

            return cliente;
        }

        /* CERRAR TICKET */
        [HttpPost("CloseTicket/{ticketId}")]
        public async Task<ActionResult<Clientes>> CloseTicket(int ticketId)
        {

            var ticket = await _context.Tickets.Where(t => t.TicketId == ticketId).FirstOrDefaultAsync();

            if (ticket == null) return NotFound();

            var cliente = await _context.Clientes
                .Where(c => c.ClienteId == ticket.ClienteId)
                .Include(c => c.Configuracion)
                .FirstOrDefaultAsync();

            if (cliente == null)
                return NotFound();

            var estatus = await _context.Estatus.FindAsync(3);
            if (estatus == null) return NotFound();

            ticket.Estatus = estatus;
            ticket.EstatusId = 3;

            ticket.FechaFinalizado = DateTime.Now;
            _context.Entry(ticket).State = EntityState.Modified;

            await _context.SaveChangesAsync();

            return cliente;
        }

        /* RESPONDER TICKET (agregar respuesta) */
        [HttpPost("ReplyTicket")]
        public async Task<ActionResult<Clientes>> ReplyTicket(AddRespuestaDTO respuesta)
        {

            var ticket = await _context.Tickets
                .Where(t => t.TicketId == respuesta.TicketId)
                .Include(t => t.Respuestas)
                .FirstOrDefaultAsync();

            if (ticket == null) return NotFound();

            var cliente = await _context.Clientes
                .Where(c => c.ClienteId == respuesta.ClienteId)
                .Include(c => c.Configuracion)
                .FirstOrDefaultAsync();

            if (cliente == null) return NotFound();

            var newRespuesta = new Respuestas
            {
                Contenido = respuesta.Contenido,
                Cliente = cliente,
                ClienteId = respuesta.ClienteId,
                Ticket = ticket,
                TicketId = respuesta.TicketId
            };


            _context.Add(newRespuesta);

            await _context.SaveChangesAsync();

            return cliente;
        }

        #endregion

        #region UTILS
        private bool ClientesExists(int id)
        {
            return _context.Clientes.Any(e => e.ClienteId == id);
        }
        private char CharacterDecrypter(char character)
        {
            char result = ' ';
            switch (character)
            {
                case 'e':
                    {
                        result = 'A';
                        break;
                    }
                case 't':
                    {
                        result = 'a';
                        break;
                    }
                case 'q':
                    {
                        result = 'B';
                        break;
                    }
                case 'k':
                    {
                        result = 'b';
                        break;
                    }
                case 'r':
                    {
                        result = 'C';
                        break;
                    }
                case 'M':
                    {
                        result = 'c';
                        break;
                    }
                case 's':
                    {
                        result = 'D';
                        break;
                    }
                case 'j':
                    {
                        result = 'd';
                        break;
                    }
                case 'f':
                    {
                        result = 'E';
                        break;
                    }
                case 'n':
                    {
                        result = 'e';
                        break;
                    }
                case 'g':
                    {
                        result = 'F';
                        break;
                    }
                case 'D':
                    {
                        result = 'f';
                        break;
                    }
                case 'p':
                    {
                        result = 'G';
                        break;
                    }
                case 'S':
                    {
                        result = 'g';
                        break;
                    }
                case 'G':
                    {
                        result = 'H';
                        break;
                    }
                case 'l':
                    {
                        result = 'h';
                        break;
                    }
                case 'F':
                    {
                        result = 'I';
                        break;
                    }
                case 'Q':
                    {
                        result = 'i';
                        break;
                    }
                case 'm':
                    {
                        result = 'J';
                        break;
                    }
                case 'B':
                    {
                        result = 'j';
                        break;
                    }
                case 'V':
                    {
                        result = 'K';
                        break;
                    }
                case 'h':
                    {
                        result = 'k';
                        break;
                    }
                case 'P':
                    {
                        result = 'L';
                        break;
                    }
                case 'A':
                    {
                        result = 'l';
                        break;
                    }
                case 'H':
                    {
                        result = 'M';
                        break;
                    }
                case 'Z':
                    {
                        result = 'm';
                        break;
                    }
                case 'o':
                    {
                        result = 'N';
                        break;
                    }
                case 'v':
                    {
                        result = 'n';
                        break;
                    }
                case 'K':
                    {
                        result = 'O';
                        break;
                    }
                case 'J':
                    {
                        result = 'o';
                        break;
                    }
                case 'b':
                    {
                        result = 'P';
                        break;
                    }
                case 'W':
                    {
                        result = 'p';
                        break;
                    }
                case 'd':
                    {
                        result = 'Q';
                        break;
                    }
                case 'E':
                    {
                        result = 'q';
                        break;
                    }
                case 'a':
                    {
                        result = 'R';
                        break;
                    }
                case 'i':
                    {
                        result = 'r';
                        break;
                    }
                case 'x':
                    {
                        result = 'S';
                        break;
                    }
                case 'O':
                    {
                        result = 's';
                        break;
                    }
                case 'L':
                    {
                        result = 'T';
                        break;
                    }
                case 'w':
                    {
                        result = 't';
                        break;
                    }
                case 'R':
                    {
                        result = 'U';
                        break;
                    }
                case 'T':
                    {
                        result = 'u';
                        break;
                    }
                case 'y':
                    {
                        result = 'V';
                        break;
                    }
                case 'X':
                    {
                        result = 'v';
                        break;
                    }
                case 'N':
                    {
                        result = 'W';
                        break;
                    }
                case 'U':
                    {
                        result = 'w';
                        break;
                    }
                case 'c':
                    {
                        result = 'X';
                        break;
                    }
                case 'I':
                    {
                        result = 'x';
                        break;
                    }
                case 'z':
                    {
                        result = 'Y';
                        break;
                    }
                case 'C':
                    {
                        result = 'y';
                        break;
                    }
                case 'Y':
                    {
                        result = 'Z';
                        break;
                    }
                case 'u':
                    {
                        result = 'z';
                        break;
                    }


                default:
                    {
                        result = character;
                        break;
                    }
            }
            return result;
        }
        #endregion

    }
}
