using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Ticket.Api.DAL;
using Ticket.Api.Models;

namespace Ticket.Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class RespuestasController : ControllerBase
    {
        private readonly TicketDbContext _context;

        public RespuestasController(TicketDbContext context)
        {
            _context = context;
        }

        #region INTERNOS
        /* OBTENER TODAS LAS RESPUESTAS */
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Respuestas>>> GetRespuestas()
        {
            return await _context.Respuestas
                .Include(r => r.Cliente)
                .Include(r => r.Ticket)
                .ToListAsync();
        }

        /* OBTENER TODAS LAS RESPUESTAS DE UN CLIENTE */
        [HttpGet("ByClienteId/{clienteId}")]
        public async Task<ActionResult<IEnumerable<Respuestas>>> GetUserRespuestas(int clienteId)
        {
            return await _context.Respuestas
                .Where(r => r.ClienteId == clienteId)
                .Include(r => r.Cliente)
                .Include(r => r.Ticket)
                .ToListAsync();
        }

        /* OBTENER TODAS LAS RESPUESTAS DE UN TICKET */
        [HttpGet("ByTicketId/{ticketId}")]
        public async Task<ActionResult<IEnumerable<Respuestas>>> GetTicketRespuestas(int ticketId)
        {
            return await _context.Respuestas
                .Where(r => r.TicketId == ticketId)
                .Include(r => r.Cliente)
                .Include(r => r.Ticket)
                .ToListAsync();
        }

        /* OBTENER 1 RESPUESTA POR ID */
        [HttpGet("{id}")]
        public async Task<ActionResult<Respuestas>> GetRespuesta(int id)
        {
            var respuestas = await _context.Respuestas
                .Include(r => r.Cliente)
                .Include(r => r.Ticket).FirstOrDefaultAsync(r => r.RespuestaId == id);

            if (respuestas == null)
            {
                return NotFound();
            }

            return respuestas;
        }

        /* UPDATE RESPUESTA */
        [HttpPut("{id}")]
        public async Task<IActionResult> PutRespuestas(int id, Respuestas respuestas)
        {
            if (id != respuestas.RespuestaId)
            {
                return BadRequest();
            }

            _context.Entry(respuestas).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!RespuestasExists(id))
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

        /* CREATE RESPUESTA */
        [HttpPost]
        public async Task<ActionResult<Respuestas>> PostRespuestas(Respuestas respuestas)
        {
            _context.Respuestas.Add(respuestas);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetRespuestas", new { id = respuestas.RespuestaId }, respuestas);
        }

        /* DELETE RESPUESTA */
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteRespuestas(int id)
        {
            var respuestas = await _context.Respuestas.FindAsync(id);
            if (respuestas == null)
            {
                return NotFound();
            }

            _context.Respuestas.Remove(respuestas);
            await _context.SaveChangesAsync();

            return NoContent();
        }
        #endregion

        #region UTILS
        private bool RespuestasExists(int id)
        {
            return _context.Respuestas.Any(e => e.RespuestaId == id);
        }
        #endregion
    }
}
