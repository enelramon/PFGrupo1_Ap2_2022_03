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
    public class TicketsController : ControllerBase
    {
        private readonly TicketDbContext _context;

        public TicketsController(TicketDbContext context)
        {
            _context = context;
        }

        /*-------- INTERNOS ---------*/

        /* OBTENER TODOS LOS TICKETS */
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Tickets>>> GetTickets()
        {
            return await _context.Tickets.Include(t => t.Tipo)
                .Include(t => t.Sistema)
                .Include(t => t.Prioridad)
                .Include(t => t.Respuestas)
                .Include(t => t.Cliente)
                .Include(t => t.Estatus).ToListAsync();
        }

        /* OBTENER MIS TICKETS */
        [HttpGet("MyTickets/{id}")]
        public async Task<ActionResult<IEnumerable<Tickets>>> GetTicketsByClienteId(int id)
        {
            return await _context.Tickets
                .Where(t => t.ClienteId == id)
                .Include(t => t.Tipo)
                .Include(t => t.Sistema)
                .Include(t => t.Prioridad)
                .Include(t => t.Respuestas)
                .Include(t => t.Cliente)
                .Include(t => t.Estatus)
                .ToListAsync();
        }

        /* OBTENER MIS TOP 5 TICKETS MAS RESPONDIDOS  */
        [HttpGet("Top5Tickets/{id}")]
        public async Task<ActionResult<IEnumerable<Tickets>>> Get5TicketsByClientId(int id)
        {
            //5 tickets con mas respuestas
            return await _context.Tickets.
                Where(t => t.ClienteId==id).
                OrderBy(t=>t.Respuestas.Count)
                .Include(t => t.Tipo)
                .Include(t => t.Sistema)
                .Include(t => t.Prioridad)
                .Include(t => t.Respuestas)
                .Include(t => t.Cliente)
                .Include(t => t.Estatus).
                Take(5).
                ToListAsync();
        }

        /* OBTENER TICKET POR ID */
        [HttpGet("{id}")]
        public async Task<ActionResult<Tickets>> GetTickets(int id)
        {
            var tickets = await _context.Tickets.Where(t => t.TicketId == id).Include(t => t.Tipo)
                .Include(t => t.Sistema)
                .Include(t => t.Prioridad)
                .Include(t => t.Respuestas)
                .Include(t => t.Cliente)
                .Include(t => t.Estatus).FirstOrDefaultAsync();

            if (tickets == null)
            {
                return NotFound();
            }

            return tickets;
        }

        /* UPDATE TICKET */
        [HttpPut("{id}")]
        public async Task<IActionResult> PutTickets(int id, Tickets tickets)
        {
            if (id != tickets.TicketId)
            {
                return BadRequest();
            }

            _context.Entry(tickets).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!TicketsExists(id))
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

        /* CREATE TICKET */
        [HttpPost]
        public async Task<ActionResult<Tickets>> PostTickets(Tickets tickets)
        {
            var cliente = await _context.Clientes.AsNoTracking().FirstOrDefaultAsync(t => t.ClienteId == tickets.ClienteId);

            if (cliente == null)
                return NotFound();

            _context.Tickets.Add(tickets);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetTickets", new { id = tickets.TicketId }, tickets);
        }

        /* DELETE TICKET */
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteTickets(int id)
        {
            var tickets = await _context.Tickets.FindAsync(id);
            if (tickets == null)
            {
                return NotFound();
            }

            _context.Tickets.Remove(tickets);
            await _context.SaveChangesAsync();

            return NoContent();
        }
        
        /*------ UTILS ------*/
        private bool TicketsExists(int id)
        {
            return _context.Tickets.Any(e => e.TicketId == id);
        }
    }
}
